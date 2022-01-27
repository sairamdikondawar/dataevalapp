import { Field } from "./field.model";
import { Page } from "./page.model";

export class Section {

    id:number;
    label: string;
    layout:number;
    fields:Array<Field>;
    status:string;
    sequence:number;
    page:Page;
    

    constructor(label: string, fields:Array<Field>) {
        this.label = label;
        this.fields=fields;
        this.page=new Page(null, null);
         
    }

    set layoutF(columns:number){
        this.layout=columns;
    }

    get layoutF(): number{
        return this.layout;
    }
}