import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { UserDetails } from '../model/common/userdetails.model';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: ''
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
  USER_PWD_SESSION_ATTRIBUTE_NAME = 'authenticatedPWD';
  USER_TYPE_SESSION_ATTRIBUTE_NAME = 'authenticatedType';
  FIRST_NAME_SESSION_ATTRIBUTE_NAME='firstName';
  LAST_NAME_SESSION_ATTRIBUTE_NAME='lastName';

   runningSubject = new BehaviorSubject(true);
   userNameSubject = new BehaviorSubject('');
   userTypeSubject = new BehaviorSubject('');
   firstNameSubject = new BehaviorSubject('');
   lastNameSubject = new BehaviorSubject('');
   userType=this.userTypeSubject.asObservable();;
   running = this.runningSubject.asObservable();
   userName=this.userNameSubject.asObservable();
   firstName=this.firstNameSubject.asObservable();
   lastName=this.lastNameSubject.asObservable();
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

setFirstName=(value :string) =>{
  this.firstNameSubject.next(value);
}

setLastName=(value :string) =>{
  this.lastNameSubject.next(value);
}

  public username: string;
  public password: string;
  public firstname:string;
  public lastname:string;
  

  constructor(private http: HttpClient , private route: ActivatedRoute,
    private router: Router, private loc: Location) {
      const angularRoute = this.loc.path();
      const url = window.location.href; 
      const domainAndApp = url.replace(angularRoute, '');
      alert(domainAndApp);
  }

  authenticationService(username: string, password: string ) {
    return this.http.get(`/api/v1/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res :any) => {
        this.username = username;
        this.password = password;
        this.firstname=res.firstName;
        this.lastname=res.lastName;
        let authority:string=JSON.stringify(res.authorities[0].authority);
        authority=authority.replace('"', '').replace('"', '').toLowerCase();;
        // alert()
        let fName:string=JSON.stringify(res.firstName);
        let lName=JSON.stringify(res.lastName);
        // alert(fName);
        this.setUserType(authority);
        this.setFirstName(fName);
        this.setLastName(lName)
        this.registerSuccessfulLogin(username, password, authority, this.firstname, this.lastname);
        this.isUserLoggedIn();
      }));
  }

  createBasicAuthToken(username: string, password: string) {
    console.log('Basic ' + window.btoa(username + ":" + password));
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string, password: string,userType:string,firstname:string,lastname:string) {
    console.log('registerSuccessfulLogin :: First Name  :: ' + firstname.toString());
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username.toString())
    sessionStorage.setItem(this.USER_PWD_SESSION_ATTRIBUTE_NAME, password.toString())
    sessionStorage.setItem(this.USER_TYPE_SESSION_ATTRIBUTE_NAME, userType.toString())
    sessionStorage.setItem(this.FIRST_NAME_SESSION_ATTRIBUTE_NAME, firstname);
    sessionStorage.setItem(this.LAST_NAME_SESSION_ATTRIBUTE_NAME, lastname);
    
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_PWD_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_TYPE_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.LAST_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.FIRST_NAME_SESSION_ATTRIBUTE_NAME);
    this.username= null;
    this.password = null;
    this.firstname=null;
    this.lastname=null;

this.isUserLoggedIn();
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    let type = sessionStorage.getItem(this.USER_TYPE_SESSION_ATTRIBUTE_NAME)
    let firstName = sessionStorage.getItem(this.FIRST_NAME_SESSION_ATTRIBUTE_NAME)
    let lastName = sessionStorage.getItem(this.LAST_NAME_SESSION_ATTRIBUTE_NAME)
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
    this.setUserType(type);
    this.setFirstName(firstName);
    this.setLastName(lastName);
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

