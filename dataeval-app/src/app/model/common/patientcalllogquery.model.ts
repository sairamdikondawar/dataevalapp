 import { CustomQuery } from "./customquery.model";

export class PatinetCallLogQuery extends CustomQuery{ 
    callType:string;
    patientName:string;
    startDate:Date;

}