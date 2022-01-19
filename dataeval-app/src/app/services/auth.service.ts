import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  public username: String;
  public password: String;

  constructor(private http: HttpClient , private route: ActivatedRoute,
    private router: Router) {

  }

  authenticationService(username: String, password: String) {
    return this.http.get(`http://localhost:8080/api/v1/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        this.username = username;
        this.password = password;
        this.registerSuccessfulLogin(username, password);
      }));
  }

  createBasicAuthToken(username: String, password: String) {
    console.log('Basic ' + window.btoa(username + ":" + password));
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: String, password: String) {
    console.log('registerSuccessfulLogin' + username.toString());
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username.toString())
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username= "";
;    this.password = "";
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    console.log("User Name :: "+user);
    if (user === null) {
      // this.router.navigateByUrl("/");
      return true
    }
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }
}

