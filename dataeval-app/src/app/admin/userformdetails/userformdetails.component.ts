import {
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output
} from "@angular/core";


import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { of } from "rxjs";
import { catchError } from "rxjs/operators";
import { Field } from "src/app/model/field.model";
import { Page } from "src/app/model/page.model"; 
import { CreateUserForm } from "src/app/model/user-form.model";
import { CommonService } from "src/app/services/common.service";

import { MatStep, MatStepper } from "@angular/material/stepper";
import { ActivatedRoute } from "@angular/router";
import { UserStep } from "src/app/model/user/user-step.model";

@Component({
  selector: 'app-userformdetails',
  templateUrl: './userformdetails.component.html',
  styleUrls: ['./userformdetails.component.css']
})
export class UserformdetailsComponent implements OnInit {
  stepper:MatStep;
  formGroups: Array<FormGroup>;

  reviewData: any;
  masterFormFields: any;

  isEditable = false;

  id:string;
  pages: UserStep[];
  formData: Array<Array<any>>;
   


  firstPage: Page;
  constructor(private fb: FormBuilder, private commonService: CommonService,private route: ActivatedRoute) {
    // formGroups:[];
    this.formData = new Array<Array<any>>();
    

  }


  get formArray(): AbstractControl | null { return this.formGroups[0].get('formArray'); }

  trackByFn(index: number): number {
    return index;
  }



  loadFields() {
    this.formGroups = [];
    this.commonService.loadUserPages(this.id).pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        console.log("inside success :: "+ result);
        this.pages =  (result);
        console.log("inside success :: "+ this.pages);
        // this.setTestData(true);
        this.pages.forEach((page) => {
          let form: any = {};
          page.sections.forEach(section => {
    
            section.fields.forEach(field => {
    
              form[field.controlName] = new FormControl(field.answer);
            });
    
          });
          this.formGroups.push(new FormGroup(form));
          
        });
      }
      );

 

    // this.setTestData(false);
    


   

    console.log("length :: " + this.formGroups[0]);
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');

    this.loadFields();
  }

   

  nextClick(data: any, index: number): number {

    if (index == this.formGroups.length - 1 || index == this.formGroups.length - 2) {
      this.formGroups.forEach(formGroup => {
        this.formGroups[this.formGroups.length - 1].patchValue(formGroup.value);
      })
      // this.formGroups[this.formGroups.length-1].patchValue(this.formGroups[index].value);
    }
    // alert(this.formGroups.length + " -- "+index)
    return index;
  } 
    

}
