import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { UserDetails } from '../model/common/userdetails.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
  USER_PWD_SESSION_ATTRIBUTE_NAME = 'authenticatedPWD';
   

   runningSubject = new BehaviorSubject(true);
   userNameSubject = new BehaviorSubject('');
   userTypeSubject = new BehaviorSubject('');
   userType=this.userTypeSubject.asObservable();;
   running = this.runningSubject.asObservable();
   userName=this.userNameSubject.asObservable();
   userDetails: UserDetails
setRunning = (value: boolean) => {
  this.runningSubject.next(value);
}
setUserName = (value : string) => {
  this.userNameSubject.next(value);
}

setUserType= (value : string) => {
  this.userTypeSubject.next(value);
}

  public username: string;
  public password: string;
  

  constructor(private http: HttpClient , private route: ActivatedRoute,
    private router: Router) {

  }

  authenticationService(username: string, password: string) {
    return this.http.get(`http://localhost:8080/api/v1/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res :any) => {
        this.username = username;
        this.password = password;
        
        let authority:string=JSON.stringify(res.authorities[0].authority);
        authority=authority.replace('"', '').replace('"', '').toLowerCase();;
        // alert()
        this.setUserType(authority);
        this.registerSuccessfulLogin(username, password);
        this.isUserLoggedIn();
      }));
  }

  createBasicAuthToken(username: string, password: string) {
    console.log('Basic ' + window.btoa(username + ":" + password));
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string, password: string) {
    console.log('registerSuccessfulLogin' + username.toString());
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username.toString())
    sessionStorage.setItem(this.USER_PWD_SESSION_ATTRIBUTE_NAME, password.toString())
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_PWD_SESSION_ATTRIBUTE_NAME);
    this.username= null;
;    this.password = null;
this.isUserLoggedIn();
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    console.log("User Name :: "+user);
    if (user === null 
      // || this.username == null
      ) {
      this.router.navigateByUrl("/");
      this.setRunning(false);
      return false
    }
    this.setRunning(true)
    this.setUserName(user);
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''

    this.setRunning(true)
    return user
  }
//Remove this latter
  getPassword() {
    let pwd = sessionStorage.getItem(this.USER_PWD_SESSION_ATTRIBUTE_NAME)
    if (pwd === null) return ''
    return pwd
  }
  
}

