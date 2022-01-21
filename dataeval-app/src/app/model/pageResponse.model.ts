import { Page } from "./page.model";

export class PageResponse {

    content: Page[];
    totalElements:number;
    

constructor(content: Page[], totalElements:number) {
        this.content = content;
        this.totalElements=totalElements;
    }
}