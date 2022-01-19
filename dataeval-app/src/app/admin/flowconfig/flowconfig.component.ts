import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { FlowconfigService } from 'src/app/services/flowconfig.service';
import { Flowconfig } from 'src/app/model/flowconfig.model';
import { FCDataSource } from 'src/app/datasoruce/fcdatasoruce/fcdatasoruce.service';
import { tap } from 'rxjs/operators';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Role } from 'src/app/model/role.model';
import { RoleService } from 'src/app/services/role.service';
import { catchError, finalize } from "rxjs/operators";
import { Observable, BehaviorSubject, of } from "rxjs";
import { Lookup } from 'src/app/model/lookup.model';
import { FormControl, FormGroup, Validators, FormGroupDirective } from '@angular/forms';


@Component({
  selector: 'app-flowconfig',
  templateUrl: './flowconfig.component.html',
  styleUrls: ['./flowconfig.component.css']
})
export class FlowconfigComponent implements OnInit {

  flowConfigs: Flowconfig[];

  //  newFlowConfig: Flowconfig; //This is used in Crate flow Config

  roles: Lookup[];

  filterRoles: Lookup[];

  role: Lookup;

  selectedRoleId: number = 0;

  newFlowConfig1: Flowconfig;

  selectedRole: string = "-- Select Role --";
  newFlowConfig = new Flowconfig(0, "");


  // displayedColumns = ['id', 'name'];
  // dataSource: MatTableDataSource<Flowconfig>;

  // @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;

  // constructor(private flowconfigService: FlowconfigService) {
  // Create 100 users
  // const users: UserData[] = [];
  // for (let i = 1; i <= 100; i++) { users.push(createNewUser(i)); }

  // Assign the data to the data source for the table to render
  // this.dataSource = new MatTableDataSource(users);





  // }



  /**
   * Set the paginator and sort after the view init since this component will
   * be able to query its view for the initialized paginator and sort.
   */
  // ngAfterViewInit() {
  //   this.dataSource.paginator = this.paginator;
  //   this.dataSource.sort = this.sort;
  // }

  // applyFilter(filterValue: string) {
  //   filterValue = filterValue.trim(); // Remove whitespace
  //   filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
  //   this.dataSource.filter = filterValue;
  // }


  //MEtrial Spring impl
  //OLD
  // ngOnInit(): void {

  //   console.log("Inside FlowconfigComponent init");
  //   this.flowconfigService.flowconfigService().subscribe( (result) => {
  //     console.log(result);
  //   });
  // }


  fcDataSource: FCDataSource;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private flowconfigservice: FlowconfigService,
    private modalService: NgbModal,
    private roleService: RoleService) {

    this.role = new Lookup(0, '');
  }
  displayedColumns = ['id', 'flowName', 'role', 'actions'];


  ngOnInit() {
    this.fcDataSource = new FCDataSource(this.flowconfigservice);
    this.fcDataSource.loadTodos();

    this.roleService.roleService()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.roles = (result);
      }
      );

  }

  ngAfterViewInit() {
    this.fcDataSource.counter$
      .pipe(
        tap((count: any) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page
      .pipe(
        tap(() => this.loadTodos())
      )
      .subscribe();
  }

  loadTodos() {
    this.fcDataSource.loadTodos(this.paginator.pageIndex, this.paginator.pageSize);



  }

  closeResult = '';


  open(content: any) {
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

  selectRole(event: any) {

    console.log(event.target.value);
    console.log("Inside Select Role :" + event.target.value)

    this.role = filterByString(this.roles, event.target.value);

    this.newFlowConfig.role.id = this.role.id;
    this.newFlowConfig.role.roleName = this.role.name;
    this.selectedRole = this.role.name;
    this.selectedRoleId = this.role.id;




    console.log("Inside Select Role :" + this.role.name)
  }

  saveFlowConfig() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.newFlowConfig.flowName = this.flowConfigForm.controls.flowConfigName.value;

    this.newFlowConfig1 = new Flowconfig(0, "");
    // this.flowConfigs.push(this.newFlowConfig);
    // 



    this.flowconfigservice.createFlow(this.newFlowConfig)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
      }
      );
    this.flowConfigForm.reset();
    this.modalService.dismissAll();
    this.fcDataSource.loadTodos();
  }


  flowConfigForm = new FormGroup({
    flowConfigName: new FormControl(this.newFlowConfig.flowName, [Validators.required,
    Validators.minLength(4)]),
    roleName: new FormControl(''),

  });

  flowConfigEditForm = new FormGroup({
    flowConfigEName: new FormControl(this.newFlowConfig.flowName, [Validators.required,
    Validators.minLength(4)]),
    roleName: new FormControl('', [Validators.required]),
    id: new FormControl('')

  });

  openEdit(targetModal: any, editFlow: Flowconfig) {

    console.log("inside edit flow : " + editFlow)

    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
    this.newFlowConfig=editFlow;
    this.flowConfigEditForm.patchValue({
      id: editFlow.id,
      flowConfigEName: editFlow.flowName,
      roleName: editFlow.role.roleName,
    });
    this.selectedRole = editFlow.role.roleName;
    this.role = this.roles[0];
    console.log(editFlow.role.roleName);
    this.selectedRoleId = editFlow.role.id;
    console.log(this.role);
  }
  get flowConfigName() { return this.flowConfigForm.get('flowConfigName'); }

  get flowConfigEName() { return this.flowConfigEditForm.get('flowConfigEName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.flowConfigForm.reset();
  }

  updateFlowConfig() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.newFlowConfig.flowName=this.flowConfigEditForm.controls.flowConfigEName.value;
 
    
    this.flowconfigservice.updateFlow(this.newFlowConfig)
    .pipe(
        catchError(() => of([]))
         
    )
    .subscribe((result) => {
        console.log(result + " result");
        this.fcDataSource.loadTodos();
    }
    );
    this.flowConfigEditForm.reset();
    this.modalService.dismissAll();
   
  }



}


function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}



/** Builds and returns a new User. */
// function createNewUser(id: number): UserData {
//   const name =
//       NAMES[Math.round(Math.random() * (NAMES.length - 1))] + ' ' +
//       NAMES[Math.round(Math.random() * (NAMES.length - 1))].charAt(0) + '.';

//   return {
//     id: id.toString(),
//     name: name,
//     progress: Math.round(Math.random() * 100).toString(),
//     color: COLORS[Math.round(Math.random() * (COLORS.length - 1))]
//   };
// }

/** Constants used to fill up our data base. */
const COLORS = ['maroon', 'red', 'orange', 'yellow', 'olive', 'green', 'purple',
  'fuchsia', 'lime', 'teal', 'aqua', 'blue', 'navy', 'black', 'gray'];
const NAMES = ['Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack',
  'Charlotte', 'Theodore', 'Isla', 'Oliver', 'Isabella', 'Jasper',
  'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'];

// export interface UserData {
//   id: string;
//   name: string;
//   progress: string;
//   color: string;
// }


