import { User } from "../user.model";

export class UserForm{
    id:number;
    user:User;
    creationDate:Date;
    createBy:string;
    updateBy:string;
    updatedDate:Date;
}