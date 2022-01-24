import { DataSource } from '@angular/cdk/table'; 
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";   
import { catchError, finalize } from "rxjs/operators";  
import { Injectable } from '@angular/core';  
import { UserForm } from 'src/app/model/user/userform.model';
import { UserFormResponse } from 'src/app/model/user/user-form-response.model';
import { UserFormService } from 'src/app/services/userform.service';

@Injectable({
    providedIn: 'root'
  })
export class UserFormDataSouce implements DataSource<UserForm> {

  private todoSubject = new BehaviorSubject<UserForm[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private userFormService: UserFormService) { }
 

  connect(collectionViewer: CollectionViewer): Observable<UserForm[]> {
    return this.todoSubject.asObservable();
}

disconnect(collectionViewer: CollectionViewer): void {
    this.todoSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
}

list(pageNumber = 0, pageSize = 10) {
    this.loadingSubject.next(true);
    this.userFormService.list({ page: pageNumber, size: pageSize })
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: UserFormResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}

}
