import { Lookup } from "./lookup.model";

export class Field {

    label: string;
    type: string;
    controlName: string;
    _readonly: boolean=false;
    options:Array<Lookup>;
    required:boolean=false;

    constructor(label: string, controlName: string) {
        this.label = label;
        this.controlName = controlName;

    }

    get readonly(): boolean {
        return this._readonly;
    }
    set readonly(readonly: boolean) {
        this._readonly = readonly;
    }

    get requiredF(): boolean {
        return this.required;
    }
    set requiredF(required: boolean) {
        this.required = required;
    }
}