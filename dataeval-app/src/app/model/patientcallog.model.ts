import { User } from "./user.model";

export class  PatientCallLog{
     id:number;
     callRecordStatus:string;
     patientStatus:string;
     visitDate:Date;
     user:User;
     callType:string;
     careTeamMemberFirstName:string;
     careTeamMemberLastName:string;
     signature:string;
     nextMonthAppointmentDate:Date;
     additionalNotes:string;
     managingSymptoms:string;
     measurableTreatmentOutcome:string;
     healthConditionsToDiscuss:string;
     remaingTime:number;
     timeSpentInSession:number;
     totalTimeSpent:number;
     creationDate:Date;

}