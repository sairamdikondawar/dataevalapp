import { Field } from "./field.model";

export class CreateUserForm {

    fields:Array<Field>;

    constructor(fields:Array<Field>) {
        this.fields=fields;

    }



}