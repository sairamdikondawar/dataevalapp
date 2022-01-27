import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from "rxjs";
import { catchError, tap } from 'rxjs/operators';
import { QuestionDataSouce } from 'src/app/datasoruce/fcdatasoruce/questiondatasouce.service';
import { QuestionQuery } from 'src/app/model/common/questionquery.model';
import { Lookup } from 'src/app/model/lookup.model';
import { Question } from 'src/app/model/question.model';
import { Section } from 'src/app/model/section.model';
import { CommonService } from 'src/app/services/common.service';
import { QuestionService } from 'src/app/services/question.service';
import { RoleService } from 'src/app/services/role.service';


@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {


  roles: Lookup[];

  types: Lookup[];

  sections: Lookup[];

  filterTypes: Lookup[];

  type: Lookup;

  selectedRoleId: number = 0;

  currentPageIndex:number=0;

  searchForm:FormGroup;

  numberOfPages :number=0;



  selectedType: string = "-- Select Role --";
  questionConfig = new Question(0, "", "", "");


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;


  questionDataSouce: QuestionDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private questionService: QuestionService, private roleService:RoleService,
    private modalService: NgbModal,
    private commonService: CommonService) {

    this.type = new Lookup(0, '');

    
  }
  displayedColumns = ['id', 'name', 'type', 'userType','status', 'sequence', 'section', 'required', 'actions'];


  ngOnInit() {
    this.questionDataSouce = new QuestionDataSouce(this.questionService);
    this.questionDataSouce.sort = this.sort;
    this.questionDataSouce.list();

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
        sname:new FormControl(''),
        qname:new FormControl('')
      })

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
    this.questionDataSouce.query=new QuestionQuery();
    if(this.searchForm.controls.sname.value!=null)
      this.questionDataSouce.query.sectionId=this.searchForm.controls.sname.value;
    this.questionDataSouce.query.qName=this.searchForm.controls.qname.value!=null ? this.searchForm.controls.qname.value : "";
    this.questionDataSouce.list(this.paginator.pageIndex, this.paginator.pageSize);



  }

  closeResult = '';


  open(content: any) {
    this.questionConfig = new Question(0, "", "", "");
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

  save() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.questionConfig.label = this.questionForm.controls.questionName.value;

    this.questionConfig.status = this.questionForm.controls.status.value;

    this.questionConfig.userTypesList=this.questionForm.controls.userType.value;



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
    questionName: new FormControl(this.questionConfig.label, [Validators.required,
    Validators.minLength(4)]),
    type: new FormControl(''),
    status: new FormControl('Active', [Validators.required]),
    section: new FormControl('', [Validators.required]),
    userType: new FormControl('', [Validators.required])
  });



  questionEditForm = new FormGroup({
    questionEName: new FormControl(this.questionConfig.label, [Validators.required,
    Validators.minLength(4)]),
    type: new FormControl(this.questionConfig.type, [Validators.required]),
    id: new FormControl(this.questionConfig.id),
    status: new FormControl(this.questionConfig.status, [Validators.required]),
    section: new FormControl(this.questionConfig.section.id, [Validators.required]),
    userType: new FormControl(this.questionConfig.userTypesList, [Validators.required])
  });

  openEdit(targetModal: any, editFlow: Question) {

    console.log("inside edit flow : " + editFlow)

    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });

    this.questionConfig = editFlow;

    if (editFlow.section == null) {
      editFlow.section = new Section(null, null);
    }



    this.questionEditForm.patchValue({
      id: editFlow.id,
      questionEName: editFlow.label,
      type: editFlow.type,
      status: editFlow.status,
      section: (editFlow.section != null ? editFlow.section.id : null),
      userType:this.questionConfig.userTypesList
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
    this.questionForm = new FormGroup({
      questionName: new FormControl(this.questionConfig.label, [Validators.required,
      Validators.minLength(4)]),
      type: new FormControl(''),
      status: new FormControl('Active', [Validators.required]),
      userType: new FormControl('', [Validators.required])
    });
  }

  updateQuestion() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.questionConfig.label = this.questionEditForm.controls.questionEName.value;
    this.questionConfig.status = this.questionEditForm.controls.status.value;
    this.questionConfig.userTypesList=this.questionEditForm.controls.userType.value;
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

  selectSection(event: any) {

    // console.log(event.target.value);
    console.log("Inside Select Section :" + event.target.value)

    this.questionConfig.section.id = event.target.value;
    
    console.log("Inside Select Section :" + event.target.value)
  }

  sortData(sort: Sort) {
 
    this.questionDataSouce.sort = sort;
    this.questionDataSouce.list();
  }

  onPaginateChange(event:any){
    this.currentPageIndex= event.pageIndex ==  0 ? 0 :(event.pageIndex)*10;
  }

  getNumberOfPages(){
    return this.paginator.getNumberOfPages();
  }

  search()
  { 
  if(this.searchForm.controls.sname.value!=null)
     this.questionDataSouce.query.sectionId=this.searchForm.controls.sname.value;
    this.questionDataSouce.query.qName=this.searchForm.controls.qname.value !=null ? this.searchForm.controls.qname.value : "";
    this.questionDataSouce.list();
  }
  resetSearch()
  {
    this.questionDataSouce.query=new QuestionQuery();
    this.questionDataSouce.query.sectionId='';
    this.questionDataSouce.query.qName="";
    this.searchForm.controls.sname.setValue("");
    this.searchForm.reset();
    this.questionDataSouce.list();
    // alert(this.searchForm.controls.sname.value +  "  "+this.questionDataSouce.query.sectionId)
  }

  getToolTipData(issueId: string): string { 
    return issueId;
}

}


function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}
