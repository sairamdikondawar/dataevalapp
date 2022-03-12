import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AlertService } from 'src/app/alert';
import { ChangePassword } from 'src/app/model/changepassword.model';
import { ChangePasswordService } from 'src/app/services/change-password.service';
import ConfirmPassword from 'src/app/validator/confirm-password';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.sass']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private service: ChangePasswordService, private alertService:AlertService) { }

  changepwdform: FormGroup;
  model:ChangePassword;

  options = {
    autoClose: true,
    keepAfterRouteChange: false
};

  ngOnInit(): void {

    this.changepwdform = this.initForm();
  }


  initForm() : FormGroup{
    return new FormGroup({
      currentpassword: new FormControl('', [Validators.required]),
      newpassword: new FormControl('', [Validators.required,
      Validators.minLength(8)]),
      confirmpassword: new FormControl('', [Validators.required,
        Validators.minLength(8)])
    },{
       validators: [ConfirmPassword.match('newpassword', 'confirmpassword')]
    });
  }

  save(){
      this.model=new ChangePassword();
      this.model.confirmPassword=this.changepwdform.controls.confirmpassword.value;
      this.model.currentPassword=this.changepwdform.controls.currentpassword.value;
      this.model.newPassword=this.changepwdform.controls.newpassword.value;
      
      this.service.update(this.model).pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        // this.dataSource.list();
        this.changepwdform.reset();
        this.changepwdform = this.initForm();
        this.alertService.success("Password Updated Successfully..", this.options)
       
      }
      );;
  }

  resetCreateForm(formData: any, formDirective: FormGroupDirective) {
    this.changepwdform.reset();
    this.changepwdform = this.initForm();
     
  }

  password(formGroup: FormGroup) {
    const { value: password } = formGroup.get('password');
    const { value: confirmPassword } = formGroup.get('confirmpassword');
    return password === confirmPassword ? null : { passwordNotMatch: true };
  }

  get newpassword() { return this.changepwdform.get('newpassword'); }

  get currentpassword() { return this.changepwdform.get('currentpassword'); }

  get confirmpassword() { return this.changepwdform.get('confirmpassword'); }

}
