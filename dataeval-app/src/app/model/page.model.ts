import { Section } from "./section.model";

export class Page {

    label: string;
     sections:Array<Section>;
    

constructor(label: string, sections:Array<Section>) {
        this.label = label;
        this.sections=sections;
    }
}