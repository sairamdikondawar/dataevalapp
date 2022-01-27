



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



   sequenceOptions:Array<number>;

  filterTypes: Lookup[];

  type: Lookup;

  selectedRoleId: number = 0;
  currentPageIndex:number=0;



  selectedType: string = "-- Select Role --";
  model = new Page(null, null);


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;


  dataSource: PageDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: PageService,
    private modalService: NgbModal,
    private commonService: CommonService) {

    this.type = new Lookup(0, '');
  }
  //, 'actions'
  displayedColumns = ['id', 'name',   'status', 'sequence'];


  ngOnInit() {
    this.dataSource = new PageDataSouce(this.service);
    this.dataSource.sort = this.sort;
    this.dataSource.list();
    this.sequenceOptions=[];
    for (var _i = 1; _i < 100; _i++) {
      this.sequenceOptions.push(_i);
      console.log(this.sequenceOptions);
  }
      

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
    this.model = new Page(null, null);
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
 
    console.log('inside submit');

    this.model.label = this.createForm.controls.sName.value;

    this.model.status = this.createForm.controls.status.value;
    this.model.sequence=this.createForm.controls.sequence.value;

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
    type: new FormControl(''),
    status: new FormControl('Active', [Validators.required]),
    sequence: new FormControl('', [Validators.required])

  });



  editForm = new FormGroup({
    eName: new FormControl(this.model.label, [Validators.required,
    Validators.minLength(4)]),
    id: new FormControl(this.model.id),
    status: new FormControl(this.model.status, [Validators.required]),
    sequence: new FormControl(this.model.sequence, [Validators.required])
  });

  openEdit(targetModal: any, selectedModel: Page) {

    console.log("inside edit flow : " + selectedModel)

    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });

    this.model = selectedModel; 
    this.editForm.patchValue({
      id: selectedModel.id,
      eName: selectedModel.label, 
      status: selectedModel.status,
      sequence:selectedModel.sequence
    }); 
  }
  get sName() { return this.createForm.get('sName'); }

  get eName() { return this.editForm.get('eName'); }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.createForm.reset();
    this.createForm = new FormGroup({
      sName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      type: new FormControl(''),
      status: new FormControl('Active', [Validators.required]),
      sequence: new FormControl('', [Validators.required])
    });
  }

  update() {
 
    console.log('inside submit');

    this.model.label = this.editForm.controls.eName.value;
    this.model.status = this.editForm.controls.status.value;
    this.model.sequence=this.editForm.controls.sequence.value;
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

    alert(sort.active + " : " + sort.direction);
    this.dataSource.sort = sort;
    this.dataSource.list();
  }

}


function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}

