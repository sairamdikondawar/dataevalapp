import { Field } from "./field.model";

export class Section {

    label: string;
    _layout:number=1;
    fields:Array<Field>;
    

constructor(label: string, fields:Array<Field>) {
        this.label = label;
        this.fields=fields;
         
    }

    set layout(columns:number){
        this._layout=columns;
    }

    get layout(): number{
        return this._layout;
    }
}