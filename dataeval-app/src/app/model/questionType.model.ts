export class QuestionType {

    id: number;

    type: string;

    status: string;

constructor(id: number, status: string, type:string) {
        this.id = id;
        this.type = type;
        this.status =status;
    }
}
