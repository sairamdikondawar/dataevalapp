import { DataSource } from '@angular/cdk/table'; 
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";   
import { catchError, finalize } from "rxjs/operators";  
import { Injectable } from '@angular/core';  
import { UserForm } from 'src/app/model/user/userform.model';

import { Section } from '../model/section.model';
import { SectionService } from '../services/section.service';
import { SectionResponse } from '../model/response/section-response.model';
import { Page } from '../model/page.model';
import { PageService } from '../services/page.service';
import { PageResponse } from '../model/pageResponse.model';
import { Sort } from '@angular/material/sort';

@Injectable({
    providedIn: 'root'
  })
export class PageDataSouce implements DataSource<Page> {

    sort:Sort;
  private todoSubject = new BehaviorSubject<Page[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private service: PageService) { }
 
  connect(collectionViewer: CollectionViewer): Observable<Page[]> {
    return this.todoSubject.asObservable();
}

disconnect(collectionViewer: CollectionViewer): void {
    this.todoSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
}

list(pageNumber = 0, pageSize = 10) {
    this.loadingSubject.next(true);
    this.service.list({ page: pageNumber, size: pageSize })
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((result: PageResponse) => {
            this.todoSubject.next(result.content);
            this.countSubject.next(result.totalElements);
        }
        );
}

}
