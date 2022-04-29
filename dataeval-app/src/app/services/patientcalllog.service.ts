import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { PatientCallLog } from "../model/patientcallog.model"; 

@Injectable({
    providedIn: 'root'
  })
  export class PatientCallLogService{

    constructor(private http: HttpClient) { 

    }
  
    
  
  list(params :any): Observable<any>{
      const endpoint = '/api/v1/manage-patientcalllog/patientcalllogs?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
       
      return this.http.get(endpoint, { params });
  }

  allList(params :any): Observable<any>{
    const endpoint = '/api/v1/manage-patientcalllog/patientcalllogshistory?'+encodeURI('searchCriteria={ "pageNo":0,"pageSize":10}');//environment.apiUrl + "/todos";
     
    return this.http.get(endpoint, { params });
}
  
  create(data:PatientCallLog): Observable<any>{
    const endpoint = '/api/v1/manage-patientcalllog/patientcalllog';
    return this.http.post(endpoint, data);
  
    
  }
  
  update(data:PatientCallLog): Observable<any>{
    const endpoint = '/api/v1/manage-patientcalllog/patientcalllog/'+data.id;
    return this.http.put(endpoint, data);  
  }
   
   
  
  get(id :any): Observable<any>{
    const endpoint = '/api/v1/manage-patientcalllog/patientcalllog/'+id;
     
    return this.http.get(endpoint);
  }

  getByPatient(id :any): Observable<any>{
    const endpoint = '/api/v1/manage-patientcalllog/patientcalllog/patient/'+id;
     
    return this.http.get(endpoint);
  }

  }