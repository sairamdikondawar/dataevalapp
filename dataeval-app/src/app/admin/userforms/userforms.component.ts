import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { UserFormDataSouce } from 'src/app/datasoruce/fcdatasoruce/ufdatasouce.service';
import { Lookup } from 'src/app/model/lookup.model';
import { UserForm } from 'src/app/model/user/userform.model';
import { CommonService } from 'src/app/services/common.service';
import { UserFormService } from 'src/app/services/userform.service';

@Component({
  selector: 'app-userforms',
  templateUrl: './userforms.component.html',
  styleUrls: ['./userforms.component.css']
})
export class UserformsComponent implements OnInit {










  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;


  questionDataSouce: UserFormDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private userFormService: UserFormService,
    private modalService: NgbModal,
    private commonService: CommonService, private router: Router,) {


  }
  displayedColumns = ['id', 'flowName', 'role', 'status', 'required', 'actions'];


  ngOnInit() {
    this.questionDataSouce = new UserFormDataSouce(this.userFormService);
    this.questionDataSouce.list();
  }

  ngAfterViewInit() {
    this.questionDataSouce.counter$
      .pipe(
        tap((count: any) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page
      .pipe(
        tap(() => this.list())
      )
      .subscribe();
  }

  list() {
    this.questionDataSouce.list(this.paginator.pageIndex, this.paginator.pageSize);
  }



  view(formId: number) {
    this.router.navigate(['/userformdetails/' + formId]
    );

  }



}



