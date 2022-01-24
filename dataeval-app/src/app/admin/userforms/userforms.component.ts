import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { UserFormDataSouce } from 'src/app/datasoruce/fcdatasoruce/ufdatasouce.service';
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


 
  types: Lookup[];

  sections: Lookup[];

  filterTypes: Lookup[];

  type: Lookup;

  selectedRoleId: number = 0;

 

  selectedType: string = "-- Select Role --";
  userForm = new UserForm();


  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;

  
  questionDataSouce: UserFormDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private userFormService: UserFormService,
    private modalService: NgbModal,
    private commonService: CommonService, private router: Router,) {

    this.type = new Lookup(0, '');
  }
  displayedColumns = ['id', 'flowName', 'role','status', 'required', 'actions'];


  ngOnInit() {
    this.questionDataSouce = new UserFormDataSouce(this.userFormService);
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


  view(formId: number) {

    this.router.navigate(['/userformdetails/'+formId] 
    );
     
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

    // this.userForm.type = this.type.name;
    // this.userForm.role.roleName = this.role.name;
    // this.selectedRole = this.role.name;
    // this.selectedRoleId = this.role.id;




    console.log("Inside Select Role :" + this.type.name)
  }

}

function filterByString(data: Lookup[], s: string) {
  return data.filter(e => (e.name == s))[0];
}

