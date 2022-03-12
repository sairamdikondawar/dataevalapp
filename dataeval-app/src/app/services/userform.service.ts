import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { CreateUserForm } from "../model/user-form.model";
import { UserForm } from "../model/user/userform.model";

@Injectable({
    providedIn: 'root'
  })
  export class UserFormService{

    constructor(private http: HttpClient) { 

    }
  
  list(params :any): Observable<any>{
      const endpoint = 'http://localhost:8080/api/v1/userform-config?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
       
      return this.http.get(endpoint, { params });
  }
  
  create(data:CreateUserForm): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/userform-config/question';
    return this.http.post(endpoint, data);
  
    
  }
  
  update(data:UserForm): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/userform-config/'+data.id;
    return this.http.put(endpoint, data);  
  }
   
   
  
  get(id :any): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/userform-config/'+id;
     
    return this.http.get(endpoint);
  }

  }