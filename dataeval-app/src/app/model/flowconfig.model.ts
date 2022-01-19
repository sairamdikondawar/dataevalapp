import { Role } from "./role.model";

export class Flowconfig {

    id: number;

    flowName: string;

    role: Role;

    constructor(id: number, flowName: string) {
        this.id = id;
        this.flowName = flowName;
        this.role= new Role(0,"","");
    }
}
