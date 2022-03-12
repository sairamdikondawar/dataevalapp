import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; 
import { ChangePassword } from '../model/changepassword.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

    constructor(private http: HttpClient) {   
    } 
   
  
  update(data:ChangePassword): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/user-config/user/password/';
    return this.http.put(endpoint, data);
  }
  
  get(id :any): Observable<any>{
      const endpoint = 'http://localhost:8080/api/v1/user-config/user/'+id; 
      return this.http.get(endpoint);
    }

    getpatient(id :any): Observable<any>{
      const endpoint = 'http://localhost:8080/api/v1/user-config/patient/'+id; 
      return this.http.get(endpoint);
    }
    

}