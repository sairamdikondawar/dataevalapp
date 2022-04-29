import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; 
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient) {   
    } 
  
  list(params :any): Observable<any>{
      const endpoint = '/api/v1/user-config/users';//environment.apiUrl + "/todos";
      return this.http.get(endpoint, { params });
  }

  patientlist(params :any): Observable<any>{
    const endpoint = '/api/v1/user-config/patients';//environment.apiUrl + "/todos";
    return this.http.get(endpoint, { params });
}

  
  
  create(data:User): Observable<any>{
    const endpoint = '/api/v1/user-config/user';
    return this.http.post(endpoint, data);  
  }
  
  update(data:User): Observable<any>{
    const endpoint = '/api/v1/user-config/user/'+data.id;
    return this.http.put(endpoint, data);
  }
  
  get(id :any): Observable<any>{
      const endpoint = '/api/v1/user-config/user/'+id; 
      return this.http.get(endpoint);
    }

    getpatient(id :any): Observable<any>{
      const endpoint = '/api/v1/user-config/patient/'+id; 
      return this.http.get(endpoint);
    }
    

}