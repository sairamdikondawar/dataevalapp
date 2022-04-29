import { Role } from "./role.model";

export class User{

    id:number;
    label:string;
    firstName:string;
    lastName:string;
    dateOfBirth:Date;
    role:Role= new Role(null, null, null);
    status:string;
    address:string;
	alternateContact:string;
	insuranceNumber:string;
	medicalRecordNumber:string;
	pharmacyFaxNumber:string;
	pharmacyName:string;
	pharmacyPhoneNumber:string;
	phoneNumber:string;
	refferal:string;
    mobileNumber:string;

}