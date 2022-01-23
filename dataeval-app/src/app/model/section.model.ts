import { Field } from "./field.model";

export class Section {

    id:number;
    label: string;
    layout:number;
    fields:Array<Field>;
    status:string;
    

    constructor(label: string, fields:Array<Field>) {
        this.label = label;
        this.fields=fields;
         
    }

    set layoutF(columns:number){
        this.layout=columns;
    }

    get layoutF(): number{
        return this.layout;
    }
}