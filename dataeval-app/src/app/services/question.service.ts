import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';
import { Question } from '../model/question.model';
import { ApplicationConstants } from '../constants/applicationConstants.constants';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) { 

  }

  

list(params :any): Observable<any>{
    const endpoint = '/api/v1/question-config/questions';
    // encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
     
    return this.http.get(ApplicationConstants.baseUrl+endpoint, { params });
}


create(data:Question): Observable<any>{
  const endpoint = '/api/v1/question-config/question';
  return this.http.post(ApplicationConstants.baseUrl+endpoint, data);

  
}

update(data:Question): Observable<any>{
  const endpoint = '/api/v1/question-config/question/'+data.id;
  return this.http.put(ApplicationConstants.baseUrl+endpoint, data);  
}

get(id :any): Observable<any>{
  const endpoint = '/api/v1/question-config/question/'+id;
   
  return this.http.get(ApplicationConstants.baseUrl+endpoint);
}


}
