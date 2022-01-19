import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Flowconfig } from '../model/flowconfig.model';
import { Message } from '../model/message.model';
import { FlowconfigResponse } from '../model/flowconfigResponse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private http: HttpClient) { 
  }
 

questionTypes(): Observable<any>{

  console.log("inside questionTypes service");
    const endpoint = 'http://localhost:8080/api/v1/lookup/questionTypes';
     
    return this.http.get(endpoint);
}


}
