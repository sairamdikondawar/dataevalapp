import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { 

   


  }

  // flowconfigService() {

  //   console.log(encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}'));
  //   // return this.http.get<Message>('/api/v1/greeting');
  //   //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('javaguides' + ':' + 'password') });
  //   return this.http.get<Flowconfig[]>('/api/v1/flow-config/flowconfigs?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}'));
  // }

roleService(): Observable<any>{

  console.log("inside role service");
    const endpoint = '/api/v1/role-config/roles';
     
    return this.http.get(endpoint);
}


}
