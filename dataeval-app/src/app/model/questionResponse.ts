import { Question } from "./question.model";

export interface QuestionResponse {
    content: Question[];
    totalElements: number;
}
 