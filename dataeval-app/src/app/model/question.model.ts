import { QuestionType } from "./questionType.model";
import { Section } from "./section.model";

export class Question {

    id: number;
    label: string;
    type: string;
    status :string;
    section: Section;


constructor(id: number, type: string, label:string, status:string) {
        this.id = id;
        this.label = label;
        this.type =type;
        this.status=status;
        this.section=new Section("",null);
    }
}
