import { MatEndDate } from "@angular/material/datepicker";
import { CustomQuery } from "./customquery.model";

export class UserFormQuery extends CustomQuery{


    startDate : Date;
    endDate:Date;
    roleName:string;
    userName:string;

}