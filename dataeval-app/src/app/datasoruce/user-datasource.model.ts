import { DataSource } from '@angular/cdk/table'; 
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";   
import { catchError, finalize } from "rxjs/operators";  
import { Injectable } from '@angular/core';  
import { UserForm } from 'src/app/model/user/userform.model';

  
import { Sort } from '@angular/material/sort';
import { UserService } from '../services/user.service';
import { UserResponse } from '../model/response/user-response.model';
import { User } from '../model/user.model'; 
import { UserQuery } from '../model/common/userquery.model';

@Injectable({
    providedIn: 'root'
  })
export class UserDataSouce implements DataSource<User> {

    sort: Sort;
    query = new UserQuery();
  private todoSubject = new BehaviorSubject<User[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private service: UserService) { }
 

  connect(collectionViewer: CollectionViewer): Observable<User[]> {
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
        .subscribe((result: UserResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );

        
}

patientlist(pageNumber = 0, pageSize = 10) {
        
    this.loadingSubject.next(true);

    this.query.page = pageNumber;
    this.query.size = pageSize;

    if (this.sort != null) {
        this.query.columnName = JSON.stringify(this.sort.active);
        this.query.order = this.sort.direction == 'asc' ? 0 : 1;
    }

    this.service.patientlist(this.query)
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: UserResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}

}
