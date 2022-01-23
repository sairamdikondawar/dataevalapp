import { Section } from "./section.model";

export class Page {

    label: string;
     sections:Array<Section>;
     _layout:number=1;
    

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