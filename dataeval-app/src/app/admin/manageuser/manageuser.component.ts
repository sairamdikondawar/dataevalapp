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
  selector: 'app-manageuser',
  templateUrl: './manageuser.component.html',
  styleUrls: ['./manageuser.component.sass']
})
export class ManageuserComponent implements OnInit {


  panelOpenState = false;
  today = new Date();
  roles: Lookup[];






  selectedRoleId: number = 0;

  currentPageIndex: number = 0;

  searchForm: FormGroup;

  numberOfPages: number = 0;
  model = new User();
  disableUserType: boolean = true;


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeUserConfigModal') closeUserConfigModal: ElementRef;


  dataSource: UserDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: UserService, private roleService: RoleService,
    private modalService: NgbModal,
    private commonService: CommonService,
    private router: Router) {



  }
  displayedColumns = ['id', 'name', 'firstname', 'lastname', 'dateOfBirth', 'userType', 'status', 'actions'];


  ngOnInit() {

    this.dataSource = new UserDataSouce(this.service);
    this.dataSource.sort = this.sort;
    this.dataSource.list();







    this.roleService.roleService()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.roles = (result);
      }
      );

    this.searchForm = new FormGroup({
      uName: new FormControl(''),
      fName: new FormControl(''),
      lName: new FormControl('')
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
    this.dataSource.query = new UserQuery();
    if (this.searchForm.controls.uName.value != null)
      this.dataSource.query.userName = this.searchForm.controls.uName.value;
    // this.dataSource.query.roleName = this.searchForm.controls.rName.value != null ? this.searchForm.controls.rName.value : "";
    if (this.searchForm.controls.lName.value != null)
      this.dataSource.query.lastName = this.searchForm.controls.lName.value;
    if (this.searchForm.controls.fName.value != null)
      this.dataSource.query.firstName = this.searchForm.controls.fName.value;
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

  add() {
    this.router.navigate(['/adduser']);
  }

  edit(id: number) {
    this.router.navigate(['/edituser/' + id]);
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

    this.model.role.id = this.createForm.controls.userType.value;

    this.model.firstName = this.createForm.controls.fName.value;

    this.model.lastName = this.createForm.controls.lName.value;

    this.model.dateOfBirth = this.createForm.controls.dateOfBirth.value;

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
    fName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]),
    lName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]),
    dateOfBirth: new FormControl(this.model.dateOfBirth),
    status: new FormControl('Active', [Validators.required]),
    userType: new FormControl('', [Validators.required])
  });



  editForm = new FormGroup({
    eName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]),
    fName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]),
    lName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]),
    dateOfBirth: new FormControl(this.model.dateOfBirth),
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
      fName: editModel.firstName,
      lName: editModel.lastName,
      dateOfBirth: editModel.dateOfBirth,
      // password:editModel.password;
      status: editModel.status,
      userType: (editModel.role != null ? editModel.role.id : null)
    });

  }
  get sName() { return this.createForm.get('sName'); }

  get fName() { return this.createForm.get('fName'); }

  get efName() { return this.editForm.get('fName'); }

  get elName() { return this.editForm.get('lName'); }

  get lName() { return this.createForm.get('lName'); }

  get eName() { return this.editForm.get('eName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.createForm.reset();
    this.createForm = new FormGroup({
      sName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      fName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      lName: new FormControl(this.model.label, [Validators.required,
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
    this.model.role.id = this.editForm.controls.userType.value;
    this.model.firstName = this.editForm.controls.fName.value;
    this.model.lastName = this.editForm.controls.lName.value;
    this.model.dateOfBirth = this.editForm.controls.dateOfBirth.value;
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

  onPaginateChange(event: any) {
    this.currentPageIndex = event.pageIndex == 0 ? 0 : (event.pageIndex) * 10;
  }

  getNumberOfPages() {
    return this.paginator.getNumberOfPages();
  }

  search() {
    if (this.searchForm.controls.uName.value != null)
      this.dataSource.query.userName = this.searchForm.controls.uName.value;
    if (this.searchForm.controls.lName.value != null)
      this.dataSource.query.lastName = this.searchForm.controls.lName.value;
    if (this.searchForm.controls.fName.value != null)
      this.dataSource.query.firstName = this.searchForm.controls.fName.value;

    this.dataSource.list();
  }
  resetSearch() {
    this.dataSource.query = new UserQuery();
    this.dataSource.query.roleName = '';
    this.dataSource.query.userName = "";
    this.searchForm.controls.uName.setValue("");
    this.searchForm.controls.lName.setValue("");
    this.searchForm.controls.fName.setValue("");
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