import { DataSource } from '@angular/cdk/table';
import { Flowconfig } from 'src/app/model/flowconfig.model';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";  
import { FlowconfigService } from 'src/app/services/flowconfig.service'; 
import { catchError, finalize } from "rxjs/operators"; 
import { FlowconfigResponse } from 'src/app/model/flowconfigResponse';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
export class FCDataSource implements DataSource<Flowconfig> {

  private todoSubject = new BehaviorSubject<Flowconfig[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private flowconfigservice: FlowconfigService) { }
 

  connect(collectionViewer: CollectionViewer): Observable<Flowconfig[]> {
    return this.todoSubject.asObservable();
}

disconnect(collectionViewer: CollectionViewer): void {
    this.todoSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
}

loadTodos(pageNumber = 0, pageSize = 10) {
    this.loadingSubject.next(true);
    this.flowconfigservice.flowconfigService({ page: pageNumber, size: pageSize })
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: FlowconfigResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}

}
