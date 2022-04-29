import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';
import { Section } from '../model/section.model';
import { Page } from '../model/page.model';

@Injectable({
  providedIn: 'root'
})
export class PageService { 
  constructor(private http: HttpClient) {   
  } 

list(params :any): Observable<any>{
    const endpoint = '/api/v1/page-config/pages?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
    return this.http.get(endpoint, { params });
}


create(data:Page): Observable<any>{
  const endpoint = '/api/v1/page-config/page';
  return this.http.post(endpoint, data);  
}

update(data:Page): Observable<any>{
  const endpoint = '/api/v1/page-config/page/'+data.id;
  return this.http.put(endpoint, data);
}

get(id :any): Observable<any>{
    const endpoint = '/api/v1/page-config/page/'+id; 
    return this.http.get(endpoint);
  }
  

}
