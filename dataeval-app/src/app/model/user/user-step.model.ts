import { UserSection } from "./user-section.model";

export class UserStep{
    id:number;
	layout:number;
	label:string;
	sequence:number;
    sections:Array<UserSection>;
}