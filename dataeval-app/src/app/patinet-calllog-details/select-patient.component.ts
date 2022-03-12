import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from "rxjs";
import { catchError, tap } from 'rxjs/operators';
import { QuestionDataSouce } from 'src/app/datasoruce/fcdatasoruce/questiondatasouce.service';
import { UserDataSouce } from 'src/app/datasoruce/user-datasource.model'; 
import { UserQuery } from 'src/app/model/common/userquery.model';
import { Lookup } from 'src/app/model/lookup.model'; 
import { Role } from 'src/app/model/role.model';
import { Section } from 'src/app/model/section.model';
import { User } from 'src/app/model/user.model';
import { CommonService } from 'src/app/services/common.service'; 
import { RoleService } from 'src/app/services/role.service';
import { UserService } from 'src/app/services/user.service';



@Component({
  selector: 'app-select-patient',
  templateUrl: './select-patient.component.html',
  styleUrls: ['./select-patient.component.css']
})
export class SelectPatientComponent implements OnInit {

  

  today=new Date();
   

  filterTypes: Lookup[];

  type: Lookup;

  selectedRoleId: number = 0;

  currentPageIndex:number=0;

  searchForm:FormGroup;

  numberOfPages :number=0; 
  model = new User();


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeUserConfigModal') closeUserConfigModal: ElementRef;


  dataSource: UserDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: UserService, private roleService:RoleService,
    private modalService: NgbModal,
    private commonService: CommonService,  private router: Router) {

    this.type = new Lookup(0, '');

    
  }
  displayedColumns = ['id',   'firstname', 'lastname' ,'dateOfBirth',  'userType','status', 'actions'];


  ngOnInit() {
    this.dataSource = new UserDataSouce(this.service);
    this.dataSource.sort = this.sort;
    this.dataSource.patientlist();

      

      this.searchForm=new FormGroup({
        uName:new FormControl(''),
        rName:new FormControl('')
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
    // this.dataSource.query.roleName=this.searchForm.controls.rName.value!=null ? this.searchForm.controls.rName.value : "";
    this.dataSource.list(this.paginator.pageIndex, this.paginator.pageSize);



  }
 
 
  sortData(sort: Sort) { 
    this.dataSource.sort = sort;
    this.dataSource.list();
  }

  onPaginateChange(event:any){
    this.currentPageIndex= event.pageIndex ==  0 ? 0 :(event.pageIndex)*10;
  }

  getNumberOfPages(){
    return this.paginator.getNumberOfPages();
  }

  search()
  { 
  if(this.searchForm.controls.uName.value!=null)
     this.dataSource.query.userName=this.searchForm.controls.uName.value;
    this.dataSource.query.roleName=this.searchForm.controls.rName.value !=null ? this.searchForm.controls.rName.value : "";
    this.dataSource.patientlist();
  }
  resetSearch()
  {
    this.dataSource.query=new UserQuery(); 
    this.dataSource.query.userName="";
    this.searchForm.controls.uName.setValue(""); 
    this.searchForm.reset();
    this.dataSource.patientlist();
    // alert(this.searchForm.controls.sname.value +  "  "+this.dataSource.query.sectionId)
  }

  getToolTipData(issueId: string): string { 
    return issueId;
}

selectPatient(id:number)
  {
    this.router.navigate(['/patientcalllog/'+id]);
  }

  navigateBack()
  {
    this.router.navigate(['/managepatient']);
  }

}

function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}