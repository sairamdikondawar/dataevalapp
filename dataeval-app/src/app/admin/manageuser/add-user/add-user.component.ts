import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AlertService } from 'src/app/alert';
import { Lookup } from 'src/app/model/lookup.model';
import { Role } from 'src/app/model/role.model';
import { User } from 'src/app/model/user.model';
import { CommonService } from 'src/app/services/common.service';
import { RoleService } from 'src/app/services/role.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  
  today=new Date().toISOString().slice(0, 16);
  roles: Lookup[]; 

  selectedRoleId: number = 0;

  currentPageIndex:number=0;
  
  numberOfPages :number=0; 
  model = new User();
  disableUserType:boolean=true; 

  options = {
    autoClose: true,
    keepAfterRouteChange: true
};

  constructor(private service: UserService, private roleService:RoleService, 
    private commonService: CommonService,
    private router: Router,private alertService:AlertService) { 
  }
  displayedColumns = ['id', 'name', 'firstname', 'lastname' ,'dateOfBirth',  'userType','status', 'actions'];
 
  ngOnInit() { 
      this.roleService.roleService()
      .pipe(
        catchError(() => of([]))
      )
      .subscribe((result) => {
        this.roles = (result);
      }
      ); 
      this.model = new User(); 
  }
 
  add()
  {
    this.router.navigate(['/adduser']);
  } 

  save() {
 
    console.log('inside submit');

    // this.model.label = this.createForm.controls.sName.value;

    this.model.status = this.createForm.controls.status.value; 

    // this.model.role.id=this.createForm.controls.userType.value;

    this.model.firstName=this.createForm.controls.fName.value;

    this.model.lastName=this.createForm.controls.lName.value;

    this.model.dateOfBirth=this.createForm.controls.dateOfBirth.value;
    this.model.phoneNumber=this.createForm.controls.phoneNumber.value;
    this.model.mobileNumber=this.createForm.controls.mobileNumber.value;
    this.model.medicalRecordNumber=this.createForm.controls.medicalRecordNumber.value;
    this.model.insuranceNumber=this.createForm.controls.insuranceNumber.value;
    this.model.alternateContact=this.createForm.controls.alternateContact.value;


    this.model.pharmacyName=this.createForm.controls.pharmacyName.value;
    this.model.pharmacyPhoneNumber=this.createForm.controls.pharmacyPhoneNumber.value;
    this.model.pharmacyFaxNumber=this.createForm.controls.pharmacyFaxNumber.value;
    this.model.address=this.createForm.controls.address.value;

    this.model.refferal=this.createForm.controls.refferal.value;

    this.service.create(this.model)
      .pipe(
        catchError(() => of([])) 
      )
      .subscribe((result) => {
        console.log(result + " result"); 
        this.alertService.success (result.message, this.options);
        this.navigateBack();
      }
      );
    this.createForm.reset(); 
  } 

  createForm = new FormGroup({
    // sName: new FormControl(this.model.label, [Validators.required,
    // Validators.minLength(4)]), 
    fName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]), 
    lName: new FormControl(this.model.label, [Validators.required,
        Validators.minLength(4)]),  
    dateOfBirth: new FormControl(this.model.dateOfBirth), 
    status: new FormControl('Active', [Validators.required]), 
    // userType: new FormControl('', [Validators.required]),
    address:new FormControl('', [Validators.required]),
    alternateContact:new FormControl('', [Validators.required]),
    insuranceNumber:new FormControl('', [Validators.required]),
    medicalRecordNumber:new FormControl('', [Validators.required]),
    pharmacyFaxNumber:new FormControl('', [Validators.required]),
    pharmacyName:new FormControl('', [Validators.required]),
    pharmacyPhoneNumber:new FormControl('', [Validators.required]),
    phoneNumber:new FormControl('', [Validators.pattern('[- +()0-9]+')]),
    refferal:new FormControl(''),
    mobileNumber:new FormControl('', [Validators.pattern('[- +()0-9]+')])
    
  });
 
  get sName() { return this.createForm.get('sName'); }

  get fName() { return this.createForm.get('fName'); } 

  get lName() { return this.createForm.get('lName'); } 

  get mobileNumber() { 
    return this.createForm.get('mobileNumber');
  }

  get phoneNumber() { 
    return this.createForm.get('phoneNumber');
  }


  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.createForm.reset();
    this.createForm = new FormGroup({
      // sName: new FormControl(this.model.label, [Validators.required,
      // Validators.minLength(4)]),
      fName: new FormControl(this.model.label, [Validators.required,
        Validators.minLength(4)]),
      lName: new FormControl(this.model.label, [Validators.required,
          Validators.minLength(4)]),
      status: new FormControl('Active', [Validators.required]),
      // userType: new FormControl('', [Validators.required]),
      address:new FormControl('', [Validators.required]),
      alternateContact:new FormControl('', [Validators.required]),
      insuranceNumber:new FormControl('', [Validators.required]),
      medicalRecordNumber:new FormControl('', [Validators.required]),
      pharmacyFaxNumber:new FormControl('', [Validators.required]),
      pharmacyName:new FormControl('', [Validators.required]),
      pharmacyPhoneNumber:new FormControl('', [Validators.required]),
      phoneNumber:new FormControl('', [Validators.required]),
      refferal:new FormControl(''),
      mobileNumber:new FormControl('', [Validators.required])
    });
  }

   
 

  getToolTipData(issueId: string): string { 
    return issueId;
}

navigateBack() {
  this.router.navigate(['/user']);
}

}
