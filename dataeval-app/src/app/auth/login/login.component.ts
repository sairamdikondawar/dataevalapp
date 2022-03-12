import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/services/auth.service';  

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  username: string;
  password : string;
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) {   }

  ngOnInit() {
  }

  handleLogin() {
    this.authenticationService.authenticationService(this.username, this.password).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.authenticationService.userType.subscribe((userType) => {
        
        if(userType == 'admin')
          {
            this.router.navigate(['/user']);
             location.reload();
          }else if(userType === 'doctor' ){
            this.router.navigate(['/viewpatientcalllogsearch']);
             location.reload();
          }else if(userType === 'nurse' ){
            this.router.navigate(['/managepatient']);
            location.reload();
          }});
          
       
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });      
  }
}
