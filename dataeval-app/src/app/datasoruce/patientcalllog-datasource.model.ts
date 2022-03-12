import { DataSource } from '@angular/cdk/table'; 
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";   
import { catchError, finalize } from "rxjs/operators";  
import { Injectable } from '@angular/core';   

  
import { Sort } from '@angular/material/sort'; 
import { PatientCallLog } from '../model/patientcallog.model';
import { PatinetCallLogQuery } from '../model/common/patientcalllogquery.model';
import { PatinetCallLogResponse } from '../model/response/patientcalllog-response.model';
import { PatientCallLogService } from '../services/patientcalllog.service';

@Injectable({
    providedIn: 'root'
  })
export class PatientCallLogDataSouce implements DataSource<PatientCallLog> {

    sort: Sort;
    query = new PatinetCallLogQuery();
  private todoSubject = new BehaviorSubject<PatientCallLog[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private service: PatientCallLogService) { }
 

  connect(collectionViewer: CollectionViewer): Observable<PatientCallLog[]> {
    return this.todoSubject.asObservable();
}

disconnect(collectionViewer: CollectionViewer): void {
    this.todoSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
}

list(pageNumber = 0, pageSize = 10) {
        
    this.loadingSubject.next(true);

    this.query.page = pageNumber;
    this.query.size = pageSize;

    if (this.sort != null) {
        this.query.columnName = JSON.stringify(this.sort.active);
        this.query.order = this.sort.direction == 'asc' ? 0 : 1;
    }

    this.service.list(this.query)
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: PatinetCallLogResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}

allList(pageNumber = 0, pageSize = 10) {
        
    this.loadingSubject.next(true);

    this.query.page = pageNumber;
    this.query.size = pageSize;

    if (this.sort != null) {
        this.query.columnName = JSON.stringify(this.sort.active);
        this.query.order = this.sort.direction == 'asc' ? 0 : 1;
    }

    this.service.allList(this.query)
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: PatinetCallLogResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}

}
