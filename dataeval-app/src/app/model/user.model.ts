import { Role } from "./role.model";

export class User{

    id:number;
    label:string;
    role:Role= new Role(null, null, null);
    status:string;

}