import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
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
  selector: 'app-manageuser',
  templateUrl: './manageuser.component.html',
  styleUrls: ['./manageuser.component.sass']
})
export class ManageuserComponent implements OnInit {

  


  roles: Lookup[];

  types: Lookup[];

  sections: Lookup[];

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
    private commonService: CommonService) {

    this.type = new Lookup(0, '');

    
  }
  displayedColumns = ['id', 'name',   'userType','status', 'actions'];


  ngOnInit() {
    this.dataSource = new UserDataSouce(this.service);
    this.dataSource.sort = this.sort;
    this.dataSource.list();

    this.commonService.questionTypes()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.types = (result);
      }
      );

      

    this.commonService.loadSections()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.sections = (result);
      }
      );

      this.roleService.roleService()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.roles = (result);
      }
      );

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
    this.dataSource.query.roleName=this.searchForm.controls.rName.value!=null ? this.searchForm.controls.rName.value : "";
    this.dataSource.list(this.paginator.pageIndex, this.paginator.pageSize);



  }

  closeResult = '';


  open(content: any) {
    this.model = new User();
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

   

  save() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.model.label = this.createForm.controls.sName.value;

    this.model.status = this.createForm.controls.status.value; 

    this.model.role.id=this.createForm.controls.userType.value;
    this.service.create(this.model)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.dataSource.list();
      }
      );
    this.createForm.reset();

    this.modalService.dismissAll();

  }


  createForm = new FormGroup({
    sName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]), 
    status: new FormControl('Active', [Validators.required]), 
    userType: new FormControl('', [Validators.required])
  });



  editForm = new FormGroup({
    eName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]), 
    id: new FormControl(this.model.id),

    status: new FormControl(this.model.status, [Validators.required]), 
    userType: new FormControl(this.model.role.id, [Validators.required])
  });

  openEdit(targetModal: any, editModel: User) {

    console.log("inside edit user : " + editModel)

    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });

    this.model = editModel;

    if (editModel.role == null) {
      editModel.role = new Role(null, null, null);
    }



    this.editForm.patchValue({
      id: editModel.id,
      eName: editModel.label, 
      // password:editModel.password;
      status: editModel.status,
      userType: (editModel.role != null ? editModel.role.id : null)
    });
     
  }
  get sName() { return this.createForm.get('sName'); }

  get eName() { return this.editForm.get('eName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.createForm.reset();
    this.createForm = new FormGroup({
      sName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      // password: new FormControl(''),
      status: new FormControl('Active', [Validators.required]),
      userType: new FormControl('', [Validators.required])
    });
  }

  updateQuestion() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.model.label = this.editForm.controls.eName.value;
    this.model.status = this.editForm.controls.status.value;
    this.model.role.id=this.editForm.controls.userType.value;
    this.service.update(this.model)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.dataSource.list();
      }
      );
    this.editForm.reset();
    this.modalService.dismissAll();

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
    this.dataSource.list();
  }
  resetSearch()
  {
    this.dataSource.query=new UserQuery();
    this.dataSource.query.roleName='';
    this.dataSource.query.userName="";
    this.searchForm.controls.uName.setValue("");
    this.searchForm.controls.rName.setValue("");
    this.searchForm.reset();
    this.dataSource.list();
    // alert(this.searchForm.controls.sname.value +  "  "+this.dataSource.query.sectionId)
  }

  getToolTipData(issueId: string): string { 
    return issueId;
}

}


function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}