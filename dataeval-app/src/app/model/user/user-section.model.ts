import { UserField } from "./user-fields.model";

export class UserSection{

    id:number;
    label:string;
    layout:number;
    fields:Array<UserField>;

}