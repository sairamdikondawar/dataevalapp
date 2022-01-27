import { Section } from "./section.model";

export class Page {

    id:number;
    label: string;
     sections:Array<Section>;
     _layout:number=1;
     status:string;
     sequence:number;
    

constructor(label: string, sections:Array<Section>) {
        this.label = label;
        this.sections=sections;
    }

    set layout(columns:number){
        this._layout=columns;
    }

    get layout(): number{
        return this._layout;
    }
}