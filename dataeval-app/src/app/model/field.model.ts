import { Lookup } from "./lookup.model";

export class Field {

    label: string;
    type: string;
    controlName: string;
    readonly: boolean=false;
    options:Array<Lookup>;
    required:boolean=false;
    answer:string;

    constructor(label: string, controlName: string) {
        this.label = label;
        this.controlName = controlName;

    }

    get readonlyF(): boolean {
        return this.readonly;
    }
    set readonlyF(readonly: boolean) {
        this.readonly = readonly;
    }

    get requiredF(): boolean {
        return this.required;
    }
    set requiredF(required: boolean) {
        this.required = required;
    }
}