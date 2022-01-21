import {
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output
} from "@angular/core";


import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Field } from "src/app/model/field.model";
import { Page } from "src/app/model/page.model";
import { Section } from "src/app/model/section.model";

@Component({
  selector: 'app-multiform',
  templateUrl: './multiform.component.html',
  styleUrls: ['./multiform.component.css']
})
export class MultiformComponent implements OnInit {


  formGroups: Array<FormGroup>;

  reviewData: any;
  masterFormFields: any;

  isEditable = false;

  pages: Array<Page>;
  formData: Array<Array<any>>;


  firstPage: Page;
  constructor(private fb: FormBuilder) {
    // formGroups:[];
    this.formData = new Array<Array<any>>();

  }


  get formArray(): AbstractControl | null { return this.formGroups[0].get('formArray'); }

  trackByFn(index: number): number {
    return index;
  }



  loadFields() {
    this.formGroups = [];
    this.pages = [];


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



    // // console.log("FormData :: "+formData[]);




    // // var section1= new Section("Section-1");
    // // var section2= 
    // var sections = Array<Section>();
    // var sections2 = Array<Section>();
    // var sections3 = Array<Section>();

    // var reviewSections = Array<Section>();
    // sections.push(new Section("Section-1", p1Sec1F))
    // sections.push(new Section("Section-2", p1Sec2F));

    // sections2.push(new Section("Section-21", p2Sec1F));
    // sections2.push(new Section("Section-22", p2Sec2F))

    // sections3.push(new Section("Section-31", p3Sec1F));
    // sections3.push(new Section("Section-32", p3Sec2F))

    // reviewSections = reviewSections.concat(sections).concat(sections2);
    // // .concat(sections3);

    // var firstPage = new Page("FIRST", sections);

    // var secondPage = new Page("Second", sections2);

    // var thirdPage = new Page("Third", sections3);


    // var reviewPage = new Page("Review & Submit", reviewSections);

    // this.pages.push(firstPage);

    // this.pages.push(secondPage);

    // //  this.pages.push(thirdPage);

    // this.pages.push(reviewPage);

    this.setTestData(false);
    this.setTestData(true);


    this.pages.forEach((page) => {
      let form: any = {};
      page.sections.forEach(section => {

        section.fields.forEach(field => {

          form[field.controlName] = new FormControl('');
        });

      });
      this.formGroups.push(new FormGroup(form));
    });

    console.log("length :: " + this.formGroups[0]);
  }

  ngOnInit() {
    this.loadFields();
  }

  onFormSubmit(): void {
    // this.formSubmit.emit(this.formData);
    this.reviewData = this.formGroups.reduce(

      (formGroups, currentForm) => ({ ...formGroups, ...currentForm.value }),
      {}
    );



    this.masterFormFields = Object.keys(this.reviewData);
    // alert(JSON.stringify(this.reviewData))
    console.log(JSON.stringify(this.reviewData));
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
    let p1Sec1F: Array<Field> = [];

    let p1sec1Filed1 = new Field("FirstName", "field1");
    let p1sec1Filed2 = new Field("LastName", "field2");

    p1Sec1F.push(p1sec1Filed1);
    p1Sec1F.push(p1sec1Filed2);


    let p1Sec2F: Array<Field> = [];
    let p1sec2Filed1 = new Field("Age", "field3");
    let p1sec2Filed2 = new Field("Address", "field4");

    p1Sec2F.push(p1sec2Filed1);
    p1Sec2F.push(p1sec2Filed2);

    let p2Sec1F: Array<Field> = [];
    let p2Sec1Filed1 = new Field("City", "field5");
    let p2Sec1Filed2 = new Field("State", "field6");

    p2Sec1F.push(p2Sec1Filed1)
    p2Sec1F.push(p2Sec1Filed2)


    let p2Sec2F: Array<Field> = [];
    let p2Sec2Filed1 = new Field("Pincode", "field7");
    let p2Sec2Filed2 = new Field("Country", "field8");

    p2Sec2F.push(p2Sec2Filed1)
    p2Sec2F.push(p2Sec2Filed2)


    let p3Sec1F: Array<Field> = [];
    let p3Sec1Filed1 = new Field("City", "field5");
    let p3Sec1Filed2 = new Field("State", "field6");

    p3Sec1F.push(p3Sec1Filed1)
    p3Sec1F.push(p3Sec1Filed2)


    let p3Sec2F: Array<Field> = [];
    let p3Sec2Filed1 = new Field("Pincode", "field7");
    let p3Sec2Filed2 = new Field("Country", "field8");

    p3Sec2F.push(p3Sec2Filed1)
    p3Sec2F.push(p3Sec2Filed2)



    // console.log("FormData :: "+formData[]);




    // var section1= new Section("Section-1");
    // var section2= 
    var sections = Array<Section>();
    var sections2 = Array<Section>();
    var sections3 = Array<Section>();

    var reviewSections = Array<Section>();
    sections.push(new Section("Section-1", p1Sec1F))
    sections.push(new Section("Section-2", p1Sec2F));

    sections2.push(new Section("Section-21", p2Sec1F));
    sections2.push(new Section("Section-22", p2Sec2F))

    sections3.push(new Section("Section-31", p3Sec1F));
    sections3.push(new Section("Section-32", p3Sec2F))

    
    // .concat(sections3);

    if(!isReview)
    {
      var firstPage = new Page("FIRST", sections);

    var secondPage = new Page("Second", sections2);

    var thirdPage = new Page("Third", sections3);

    this.pages.push(firstPage);

    this.pages.push(secondPage);
     //  this.pages.push(thirdPage);
    }else{
     
      reviewSections = reviewSections.concat(sections).concat(sections2);

       reviewSections.forEach(section => {

         section.fields.forEach(field =>{
              field.readonly=true;
         })
         section.layout=2;
       })

      var reviewPage = new Page("Review & Submit", reviewSections);
      this.pages.push(reviewPage);
  }
    }

    

   
   

    



}
