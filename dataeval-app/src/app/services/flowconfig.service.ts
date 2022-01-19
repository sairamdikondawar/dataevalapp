import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';

@Injectable({
  providedIn: 'root'
})
export class FlowconfigService {

 

  constructor(private http: HttpClient) { 
  
   


  }

  // flowconfigService() {

  //   console.log(encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}'));
  //   // return this.http.get<Message>('http://localhost:8080/api/v1/greeting');
  //   //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('javaguides' + ':' + 'password') });
  //   return this.http.get<Flowconfig[]>('http://localhost:8080/api/v1/flow-config/flowconfigs?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}'));
  // }

flowconfigService(params :any): Observable<any>{
    const endpoint = 'http://localhost:8080/api/v1/flow-config/flowconfigs?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
     
    return this.http.get(endpoint, { params });
}


createFlow(data:Flowconfig): Observable<any>{
  const endpoint = 'http://localhost:8080/api/v1/flow-config/flowconfig';
  return this.http.post(endpoint, data);

  
}

updateFlow(data:Flowconfig): Observable<any>{
  const endpoint = 'http://localhost:8080/api/v1/flow-config/flowconfig/'+data.id;
  return this.http.put(endpoint, data);

  
}

}
