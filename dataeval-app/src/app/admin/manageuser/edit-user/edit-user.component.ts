import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {









  today = new Date();
  roles: Lookup[];

  id:string;

  editForm:FormGroup;


  selectedRoleId: number = 0;

  currentPageIndex: number = 0;


  numberOfPages: number = 0;
  model = new User();
  disableUserType: boolean = true;

  options = {
    autoClose: true,
    keepAfterRouteChange: true
};







  constructor(private service: UserService, private roleService: RoleService,
    private commonService: CommonService,
    private router: Router, private route: ActivatedRoute,private alertService:AlertService) {



  }
  displayedColumns = ['id', 'name', 'firstname', 'lastname', 'dateOfBirth', 'userType', 'status', 'actions'];


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
    this.editForm = new FormGroup({
      eName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      fName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      lName: new FormControl(this.model.label, [Validators.required,
      Validators.minLength(4)]),
      dateOfBirth: new FormControl(this.model.dateOfBirth),
      id: new FormControl(this.model.id),
  
      status: new FormControl(this.model.status, [Validators.required]),
      userType: new FormControl(this.model.role.id, [Validators.required]),
   
      address:new FormControl(this.model.address, [Validators.required]),
    alternateContact:new FormControl(this.model.alternateContact, [Validators.required]),
    insuranceNumber:new FormControl(this.model.insuranceNumber, [Validators.required]),
    medicalRecordNumber:new FormControl(this.model.medicalRecordNumber, [Validators.required]),
    pharmacyFaxNumber:new FormControl(this.model.pharmacyFaxNumber, [Validators.required]),
    pharmacyName:new FormControl(this.model.pharmacyName, [Validators.required]),
    pharmacyPhoneNumber:new FormControl(this.model.pharmacyPhoneNumber, [Validators.required]),
    phoneNumber:new FormControl(this.model.phoneNumber, [Validators.pattern('[- +()0-9]+')]),
    refferal:new FormControl(this.model.refferal),
    mobileNumber:new FormControl(this.model.mobileNumber, [Validators.pattern('[- +()0-9]+')])
    });
    this.id = this.route.snapshot.paramMap.get('id');
    this.service.get(this.id)
    .pipe(
      catchError(() => of([]))
    )
    .subscribe((result : User) => {
      this.model=result;
       
    if (this.model.role == null) {
      this.model.role = new Role(null, null, null);
    }

    // alert(new Date(this.model.dateOfBirth).toISOString().slice(0, 10));

    this.editForm.patchValue({
      id: this.model.id,
      eName: this.model.label,
      fName: this.model.firstName,
      lName: this.model.lastName,
      dateOfBirth: new Date(this.model.dateOfBirth).toISOString().slice(0, 10),
      // password:editModel.password;
      status: this.model.status,
      userType: (this.model.role != null ? this.model.role.id : null),
      address:this.model.address,
      alternateContact:this.model.alternateContact,
      insuranceNumber:this.model.insuranceNumber,
      medicalRecordNumber:this.model.medicalRecordNumber,
      pharmacyFaxNumber:this.model.pharmacyFaxNumber,
      pharmacyName:this.model.pharmacyName,
      pharmacyPhoneNumber:this.model.pharmacyPhoneNumber,
      phoneNumber:this.model.phoneNumber,
      refferal:this.model.refferal,
      mobileNumber:this.model.mobileNumber
    });
    }
    ); 
   
}
  

  
  
 

   

  get efName() { return this.editForm.get('fName'); }

  get elName() { return this.editForm.get('lName'); }


  get eName() { return this.editForm.get('eName'); }




  updateQuestion() {

    // this.vehicleForm.controls.VehicleMake.value
    console.log('inside submit');

    this.model.label = this.editForm.controls.eName.value;
    this.model.status = this.editForm.controls.status.value;
    this.model.role.id = this.editForm.controls.userType.value;
    this.model.firstName = this.editForm.controls.fName.value;
    this.model.lastName = this.editForm.controls.lName.value;
    this.model.dateOfBirth = this.editForm.controls.dateOfBirth.value;


    this.model.phoneNumber=this.editForm.controls.phoneNumber.value;
    this.model.mobileNumber=this.editForm.controls.mobileNumber.value;
    this.model.medicalRecordNumber=this.editForm.controls.medicalRecordNumber.value;
    this.model.insuranceNumber=this.editForm.controls.insuranceNumber.value;
    this.model.alternateContact=this.editForm.controls.alternateContact.value;


    this.model.pharmacyName=this.editForm.controls.pharmacyName.value;
    this.model.pharmacyPhoneNumber=this.editForm.controls.pharmacyPhoneNumber.value;
    this.model.pharmacyFaxNumber=this.editForm.controls.pharmacyFaxNumber.value;
    this.model.address=this.editForm.controls.address.value;

    this.model.refferal=this.editForm.controls.refferal.value;

    this.service.update(this.model)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
       // alert(result.message);
        this.alertService.success(result.message, this.options);
        this.navigateBack();
      }
      );
    this.editForm.reset();


  }


  getToolTipData(issueId: string): string {
    return issueId;
  }

  navigateBack() {
    this.router.navigate(['/user']);
  }

  get mobileNumber() { 
    return this.editForm.get('mobileNumber');
  }

  get phoneNumber() { 
    return this.editForm.get('phoneNumber');
  }

}
