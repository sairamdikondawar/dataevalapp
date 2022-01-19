import { QuestionType } from "./questionType.model";

export class Question {

    id: number;
    name: string;
    type: string;
    status :string

constructor(id: number, type: string, name:string, status:string) {
        this.id = id;
        this.name = name;
        this.type =type;
        this.status=status;
    }
}
