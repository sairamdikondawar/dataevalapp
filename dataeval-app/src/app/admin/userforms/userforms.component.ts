import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { UserFormDataSouce } from 'src/app/datasoruce/fcdatasoruce/ufdatasouce.service';
import { UserQuery } from 'src/app/model/common/userquery.model';
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


   currentPageIndex:number=0;

   searchForm:FormGroup;







  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;


  dataSource: UserFormDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private userFormService: UserFormService,
    private modalService: NgbModal,
    private commonService: CommonService, private router: Router,) {


  }
  displayedColumns = ['id', 'flowName', 'role', 'status', 'required', 'actions'];


  ngOnInit() {
    this.dataSource = new UserFormDataSouce(this.userFormService);
    this.dataSource.list();

    this.searchForm=new FormGroup({
      uName:new FormControl(''),
      rName:new FormControl(''),
      startDate:new FormControl('')
    })
  }

  ngAfterViewInit() {
    this.dataSource.counter$
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

    this.dataSource.query=new UserQuery();
    if(this.searchForm.controls.uName.value!=null)
      this.dataSource.query.userName=this.searchForm.controls.uName.value;
      this.dataSource.query.startDate=this.searchForm.controls.startDate.value;
    this.dataSource.query.roleName=this.searchForm.controls.rName.value!=null ? this.searchForm.controls.rName.value : "";
    this.dataSource.list(this.paginator.pageIndex, this.paginator.pageSize);


    this.dataSource.list(this.paginator.pageIndex, this.paginator.pageSize);
  }



  view(formId: number) {
    this.router.navigate(['/userformdetails/' + formId]
    );

  }
  onPaginateChange(event:any){
    this.currentPageIndex= event.pageIndex ==  0 ? 0 :(event.pageIndex)*10;
  }

  search()
  { 
  if(this.searchForm.controls.uName.value!=null)
     this.dataSource.query.userName=this.searchForm.controls.uName.value;
     this.dataSource.query.startDate=this.searchForm.controls.startDate.value;
    this.dataSource.query.roleName=this.searchForm.controls.rName.value !=null ? this.searchForm.controls.rName.value : "";
    this.dataSource.list();
  }
  resetSearch()
  {
    this.dataSource.query=new UserQuery();
    this.dataSource.query.userName='';
    this.dataSource.query.roleName="";
    this.dataSource.query.startDate=null;
    this.searchForm.controls.startDate.setValue("");
    this.searchForm.reset();
    this.dataSource.list();
    // alert(this.searchForm.controls.sname.value +  "  "+this.dataSource.query.sectionId)
  }



}



