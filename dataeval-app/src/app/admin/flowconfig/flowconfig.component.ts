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
import { Router } from '@angular/router';


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


  fcDataSource: FCDataSource;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: FlowconfigService,
    private modalService: NgbModal,
    private roleService: RoleService, private router:Router) {

    this.role = new Lookup(0, '');
  }
  displayedColumns = ['id', 'role', 'actions'];


  ngOnInit() {
    this.fcDataSource = new FCDataSource(this.service);
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
    this.newFlowConfig.role.name = this.role.name;
    this.selectedRole = this.role.name;
    this.selectedRoleId = this.role.id;




    console.log("Inside Select Role :" + this.role.name)
  }

  save() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.newFlowConfig.flowName = this.createForm.controls.flowConfigName.value;

    this.newFlowConfig1 = new Flowconfig(0, "");
    // this.flowConfigs.push(this.newFlowConfig);  
    this.service.createFlow(this.newFlowConfig)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.createForm.reset();
    this.modalService.dismissAll();
    this.fcDataSource.loadTodos();
      }
      );
    
  }


  createForm = new FormGroup({
    flowConfigName: new FormControl(this.newFlowConfig.flowName, [Validators.required,
    Validators.minLength(4)]),
    roleName: new FormControl(''),

  });

  editForm = new FormGroup({
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
    this.editForm.patchValue({
      id: editFlow.id,
      flowConfigEName: editFlow.flowName,
      roleName: editFlow.role.name,
    });
    this.selectedRole = editFlow.role.name;
    this.role = this.roles[0];
    console.log(editFlow.role.name);
    this.selectedRoleId = editFlow.role.id;
    console.log(this.role);
  }

  viewUserForm(targetModal: any, editFlow: Flowconfig) {

    console.log("inside edit flow : " + editFlow)
     
    this.router.navigate(['/patient/' + editFlow.role.name]);
  }


  get flowConfigName() { return this.createForm.get('flowConfigName'); }

  get flowConfigEName() { return this.editForm.get('flowConfigEName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.createForm.reset();
  }

  update() {
    console.log('inside submit');

    this.newFlowConfig.flowName=this.editForm.controls.flowConfigEName.value;
 
    
    this.service.updateFlow(this.newFlowConfig)
    .pipe(
        catchError(() => of([]))
         
    )
    .subscribe((result) => {
        console.log(result + " result");
        this.fcDataSource.loadTodos();
    }
    );
    this.editForm.reset();
    this.modalService.dismissAll();
   
  }
 
}
 
function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}
 
 


