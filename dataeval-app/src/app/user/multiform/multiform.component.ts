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
import { Section } from "src/app/model/section.model";
import { CreateUserForm } from "src/app/model/user-form.model";
import { CommonService } from "src/app/services/common.service";

import { MatStep, MatStepper } from "@angular/material/stepper";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-multiform',
  templateUrl: './multiform.component.html',
  styleUrls: ['./multiform.component.css']
})
export class MultiformComponent implements OnInit {


  stepper:MatStep;
  formGroups: Array<FormGroup>;

  reviewData: any;
  masterFormFields: any;

  isEditable = false;

  pages: Page[];
  formData: Array<Array<any>>;

  role:string;
   


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

    
    this.commonService.loadPages({ role : this.role == null ? '' : this.role}).pipe(
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
    
              form[field.controlName] = new FormControl('');
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
    this.role = this.route.snapshot.paramMap.get('role');
    this.loadFields();
  }

  onFormSubmit(stepper:MatStepper): void {
    // this.formSubmit.emit(this.formData);
    this.reviewData = this.formGroups.reduce(

      (formGroups, currentForm) => ({ ...formGroups, ...currentForm.value }),
      {}

     
    );

    this.masterFormFields = Object.keys(this.reviewData);
    // alert(JSON.stringify(this.reviewData))
    console.log(JSON.stringify(this.reviewData));

    let fields:Array<Field> =[];

    let userForm=new CreateUserForm(fields);

    for(let eleme of this.masterFormFields) {
       let  field=new Field( eleme, "");
       field.answer=this.reviewData[eleme];
      console.log(JSON.stringify(field));
      fields.push(field);
    };

    console.log(JSON.stringify(userForm));

    this.commonService.submitUserForm(userForm)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        this.loadFields();
        stepper.reset();
      }
      );
    



    
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

  setTestData(isReview: boolean) {
    // let p1Sec1F: Array<Field> = [];

    // let p1sec1Filed1 = new Field("FirstName", "field1");
    // let p1sec1Filed2 = new Field("LastName", "field2");

    // p1Sec1F.push(p1sec1Filed1);
    // p1Sec1F.push(p1sec1Filed2);


    // let p1Sec2F: Array<Field> = [];
    // let p1sec2Filed1 = new Field("Age", "field3");
    // let p1sec2Filed2 = new Field("Address", "field4");

    // p1Sec2F.push(p1sec2Filed1);
    // p1Sec2F.push(p1sec2Filed2);

    // let p2Sec1F: Array<Field> = [];
    // let p2Sec1Filed1 = new Field("City", "field5");
    // let p2Sec1Filed2 = new Field("State", "field6");

    // p2Sec1F.push(p2Sec1Filed1)
    // p2Sec1F.push(p2Sec1Filed2)


    // let p2Sec2F: Array<Field> = [];
    // let p2Sec2Filed1 = new Field("Pincode", "field7");
    // let p2Sec2Filed2 = new Field("Country", "field8");

    // p2Sec2F.push(p2Sec2Filed1)
    // p2Sec2F.push(p2Sec2Filed2)


    // let p3Sec1F: Array<Field> = [];
    // let p3Sec1Filed1 = new Field("City", "field5");
    // let p3Sec1Filed2 = new Field("State", "field6");

    // p3Sec1F.push(p3Sec1Filed1)
    // p3Sec1F.push(p3Sec1Filed2)


    // let p3Sec2F: Array<Field> = [];
    // let p3Sec2Filed1 = new Field("Pincode", "field7");
    // let p3Sec2Filed2 = new Field("Country", "field8");

    // p3Sec2F.push(p3Sec2Filed1)
    // p3Sec2F.push(p3Sec2Filed2)



    // console.log("FormData :: "+formData[]);



 
    // var sections = Array<Section>();
    // var sections2 = Array<Section>();
    // var sections3 = Array<Section>();

    var reviewSections = Array<Section>();
    // sections.push(new Section("Section-1", p1Sec1F))
    // sections.push(new Section("Section-2", p1Sec2F));

    // sections2.push(new Section("Section-21", p2Sec1F));
    // sections2.push(new Section("Section-22", p2Sec2F))

    // sections3.push(new Section("Section-31", p3Sec1F));
    // sections3.push(new Section("Section-32", p3Sec2F))

    
    // .concat(sections3);

    // if(!isReview)
    // {
    //   var firstPage = new Page("FIRST", sections);

    // var secondPage = new Page("Second", sections2);

    // var thirdPage = new Page("Third", sections3);

    // this.pages.push(firstPage);

    // this.pages.push(secondPage);
    //  //  this.pages.push(thirdPage);
    // }else
    {
     
      this.pages.forEach(page =>{
        reviewSections=reviewSections.concat(page.sections);
      }) ;

       reviewSections.forEach(section => {

         section.fields.forEach(field =>{
              field.readonly=true;
              
         })
         section.layout=1;
        
       })

      var reviewPage = new Page("Review & Submit", reviewSections);
      reviewPage.layout=2;
      this.pages.push(reviewPage);
  }
    }

    

   
   

    



}
