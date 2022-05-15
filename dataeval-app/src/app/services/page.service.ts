import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';
import { Section } from '../model/section.model';
import { Page } from '../model/page.model';
import { ApplicationConstants } from '../constants/applicationConstants.constants';

@Injectable({
  providedIn: 'root'
})
export class PageService { 
  constructor(private http: HttpClient) {   
  } 

list(params :any): Observable<any>{
    const endpoint = '/api/v1/page-config/pages?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
    return this.http.get(ApplicationConstants.baseUrl+endpoint, { params });
}


create(data:Page): Observable<any>{
  const endpoint = '/api/v1/page-config/page';
  return this.http.post(ApplicationConstants.baseUrl+endpoint, data);  
}

update(data:Page): Observable<any>{
  const endpoint = '/api/v1/page-config/page/'+data.id;
  return this.http.put(ApplicationConstants.baseUrl+endpoint, data);
}

get(id :any): Observable<any>{
    const endpoint = '/api/v1/page-config/page/'+id; 
    return this.http.get(ApplicationConstants.baseUrl+endpoint);
  }
  

}
