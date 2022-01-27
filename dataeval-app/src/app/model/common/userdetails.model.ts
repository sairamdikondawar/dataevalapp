import { UserAuthorities } from "./userauth.model";

export class UserDetails{
    username:string;
    authorities: Array<UserAuthorities>;
}