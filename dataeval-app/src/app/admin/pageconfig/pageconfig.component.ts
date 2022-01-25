



import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from "rxjs";
import { catchError, tap } from 'rxjs/operators'; 
import { PageDataSouce } from 'src/app/datasoruce/page-datasource.service';
import { Lookup } from 'src/app/model/lookup.model'; 
import { Page } from 'src/app/model/page.model';
import { Section } from 'src/app/model/section.model';
import { CommonService } from 'src/app/services/common.service';
import { PageService } from 'src/app/services/page.service'; 



@Component({
  selector: 'app-pageconfig',
  templateUrl: './pageconfig.component.html',
  styleUrls: ['./pageconfig.component.sass']
})
export class PageconfigComponent implements OnInit {



  types: Lookup[];

  sections: Lookup[];

  filterTypes: Lookup[];

  type: Lookup;

  selectedRoleId: number = 0;



  selectedType: string = "-- Select Role --";
  pageConfig = new Page(null, null);


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;


  dataSource: PageDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: PageService,
    private modalService: NgbModal,
    private commonService: CommonService) {

    this.type = new Lookup(0, '');
  }
  displayedColumns = ['id', 'flowName', 'role', 'status', 'sequence', 'section', 'required', 'actions'];


  ngOnInit() {
    this.dataSource = new PageDataSouce(this.service);
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
    this.dataSource.list(this.paginator.pageIndex, this.paginator.pageSize);



  }

  closeResult = '';


  open(content: any) {
    this.pageConfig = new Page(null, null);
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

   

  saveFlowConfig() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.pageConfig.label = this.pageForm.controls.questionName.value;

    this.pageConfig.status = this.pageForm.controls.status.value;

    this.service.create(this.pageConfig)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.dataSource.list();
      }
      );
    this.pageForm.reset();

    this.modalService.dismissAll();

  }


  pageForm = new FormGroup({
    questionName: new FormControl(this.pageConfig.label, [Validators.required,
    Validators.minLength(4)]),
    type: new FormControl(''),
    status: new FormControl('ACTIVE', [Validators.required]),
    section: new FormControl('', [Validators.required])

  });



  questionEditForm = new FormGroup({
    questionEName: new FormControl(this.pageConfig.label, [Validators.required,
    Validators.minLength(4)]),
    id: new FormControl(this.pageConfig.id),
    status: new FormControl(this.pageConfig.status, [Validators.required])

  });

  openEdit(targetModal: any, editFlow: Page) {

    console.log("inside edit flow : " + editFlow)

    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });

    this.pageConfig = editFlow; 
    this.questionEditForm.patchValue({
      id: editFlow.id,
      questionEName: editFlow.label, 
      status: editFlow.status
    }); 
  }
  get questionName() { return this.pageForm.get('questionName'); }

  get questionEName() { return this.questionEditForm.get('questionEName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.pageForm.reset();
    this.pageForm = new FormGroup({
      questionName: new FormControl(this.pageConfig.label, [Validators.required,
      Validators.minLength(4)]),
      type: new FormControl(''),
      status: new FormControl('ACTIVE', [Validators.required])

    });
  }

  updatePage() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.pageConfig.label = this.questionEditForm.controls.questionEName.value;
    this.pageConfig.status = this.questionEditForm.controls.status.value;

    this.service.update(this.pageConfig)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.dataSource.list();
      }
      );
    this.questionEditForm.reset();
    this.modalService.dismissAll();

  } 
  sortData(sort: Sort) {

    alert(sort.active + " : " + sort.direction);
    this.dataSource.sort = sort;
    this.dataSource.list();
  }

}


function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}

