import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';
import { Section } from '../model/section.model';

@Injectable({
  providedIn: 'root'
})
export class SectionService { 
  constructor(private http: HttpClient) {   
  } 

list(params :any): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/pagesec-config/sections?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
    return this.http.get(endpoint, { params });
}


create(data:Section): Observable<any>{
  const endpoint = 'http://localhost:8080/api/v1/pagesec-config/section';
  return this.http.post(endpoint, data);  
}

update(data:Section): Observable<any>{
  const endpoint = 'http://localhost:8080/api/v1/pagesec-config/section/'+data.id;
  return this.http.put(endpoint, data);
}

get(id :any): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/pagesec-config/section/'+id; 
    return this.http.get(endpoint);
  }
  

}
