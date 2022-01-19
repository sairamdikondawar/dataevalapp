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
import { Question } from 'src/app/model/question.model';
import { QuestionService } from 'src/app/services/question.service';
import { QuestionDataSouce } from 'src/app/datasoruce/fcdatasoruce/questiondatasouce.service';
import { CommonService } from 'src/app/services/common.service';



@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {


 
  types: Lookup[];

  filterTypes: Lookup[];

  type: Lookup;

  selectedRoleId: number = 0;

 

  selectedType: string = "-- Select Role --";
  questionConfig = new Question(0,"", "","");


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;

  
  questionDataSouce: QuestionDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private questionService: QuestionService,
    private modalService: NgbModal,
    private commonService: CommonService) {

    this.type = new Lookup(0, '');
  }
  displayedColumns = ['id', 'flowName', 'role','status', 'actions'];


  ngOnInit() {
    this.questionDataSouce = new QuestionDataSouce(this.questionService);
    this.questionDataSouce.list();

    this.commonService.questionTypes()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.types = (result);
      }
      );

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

  closeResult = '';


  open(content: any) {
    this.questionConfig = new Question(0,"", "","");
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

  selectType(event: any) {

    console.log(event.target.value);
    console.log("Inside Select Role :" + event.target.value)

    this.type = filterByString(this.types, event.target.value);

    this.questionConfig.type = this.type.name;
    // this.questionConfig.role.roleName = this.role.name;
    // this.selectedRole = this.role.name;
    // this.selectedRoleId = this.role.id;




    console.log("Inside Select Role :" + this.type.name)
  }

  saveFlowConfig() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.questionConfig.name = this.questionForm.controls.questionName.value;
    
    this.questionConfig.status= this.questionForm.controls.status.value;

    this.questionService.create(this.questionConfig)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.questionDataSouce.list();
      }
      );
    this.questionForm.reset();
    
    this.modalService.dismissAll();
    
  }


  questionForm = new FormGroup({
    questionName: new FormControl(this.questionConfig.name, [Validators.required,
    Validators.minLength(4)]),
    type: new FormControl(''),
    status: new FormControl('ACTIVE', [Validators.required])

  });

  questionEditForm = new FormGroup({
    questionEName: new FormControl(this.questionConfig.name, [Validators.required,
    Validators.minLength(4)]),
    type: new FormControl(this.questionConfig.type, [Validators.required]),
    id: new FormControl(this.questionConfig.id),
    status: new FormControl(this.questionConfig.status, [Validators.required])

  });

  openEdit(targetModal: any, editFlow: Question) {

    console.log("inside edit flow : " + editFlow)

    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
    this.questionConfig=editFlow;
    this.questionEditForm.patchValue({
      id: editFlow.id,
      questionEName: editFlow.name,
      type: editFlow.type,
      status:editFlow.status
    });
    // this.selectedRole = editFlow.role.roleName;
    // this.role = this.roles[0];
    // console.log(editFlow.role.roleName);
    this.selectedType = editFlow.type;
    console.log(this.type);
  }
  get questionName() { return this.questionForm.get('questionName'); }

  get questionEName() { return this.questionEditForm.get('questionEName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.questionForm.reset();
    this.questionForm=new FormGroup({
      questionName: new FormControl(this.questionConfig.name, [Validators.required,
      Validators.minLength(4)]),
      type: new FormControl(''),
      status: new FormControl('ACTIVE', [Validators.required])
  
    });
  }

  updateQuestion() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.questionConfig.name=this.questionEditForm.controls.questionEName.value;
    this.questionConfig.status=this.questionEditForm.controls.status.value;
    
    this.questionService.update(this.questionConfig)
    .pipe(
        catchError(() => of([]))
         
    )
    .subscribe((result) => {
        console.log(result + " result");
        this.questionDataSouce.list();
    }
    );
    this.questionEditForm.reset();
    this.modalService.dismissAll();
   
  }



}


function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}
