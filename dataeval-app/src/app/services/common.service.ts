import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';
import { CreateUserForm} from '../model/user-form.model';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private http: HttpClient) { 
  }
 

questionTypes(): Observable<any>{

  console.log("inside questionTypes service");
    const endpoint = '/api/v1/lookup/questionTypes';
     
    return this.http.get(endpoint);
}

loadPages(params:any): Observable<any>{

  console.log("inside Pages service");
    const endpoint = '/api/v1/lookup/pages';
     
    return this.http.get(endpoint , {params});
}

lookupPages(): Observable<any>{

  console.log("inside Pages service");
    const endpoint = '/api/v1/lookup/lpages';
     
    return this.http.get(endpoint );
}

loadUserPages(id:string): Observable<any>{

  console.log("inside User Pages service");
    const endpoint = '/api/v1/userpage-config/userform/'+id;
     
    return this.http.get(endpoint);
}

loadSections(): Observable<any>{

  console.log("inside Section service");
    const endpoint = '/api/v1/lookup/sections';
     
    return this.http.get(endpoint);
}

submitUserForm(data:CreateUserForm): Observable<any>{
  const endpoint = '/api/v1/userform-config';
  return this.http.post(endpoint, data);

  
}


}
