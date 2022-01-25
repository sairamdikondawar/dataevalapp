import { DataSource } from '@angular/cdk/table';
import { Flowconfig } from 'src/app/model/flowconfig.model';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";  
import { FlowconfigService } from 'src/app/services/flowconfig.service'; 
import { catchError, finalize } from "rxjs/operators"; 
import { FlowconfigResponse } from 'src/app/model/flowconfigResponse';
import { Injectable } from '@angular/core';
import { Question } from 'src/app/model/question.model';
import { QuestionService } from 'src/app/services/question.service';
import { QuestionResponse } from 'src/app/model/questionResponse';
import { MatSort, Sort } from '@angular/material/sort';
import { CustomSort } from 'src/app/model/common/customsort.model';
import { CustomQuery } from 'src/app/model/common/customquery.model';

@Injectable({
    providedIn: 'root'
  })
export class QuestionDataSouce implements DataSource<Question> {

  sort:Sort;
  private todoSubject = new BehaviorSubject<Question[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private questionservice: QuestionService) { }
 

  connect(collectionViewer: CollectionViewer): Observable<Question[]> {
    return this.todoSubject.asObservable();
}

disconnect(collectionViewer: CollectionViewer): void {
    this.todoSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
}

list(pageNumber = 0, pageSize = 10) {
    this.loadingSubject.next(true);
     
    let sortOrders:Array<CustomSort>=[];

   var testquery= new CustomQuery();
   testquery.page=pageNumber;
   testquery.size=pageSize;
    if(this.sort !=null)
    { 
        testquery.columnName=JSON.stringify(this.sort.active);
        testquery.order=this.sort.direction ==  'asc'?  0 : 1; 
    }

    this.questionservice.list(testquery)
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: QuestionResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}



}
