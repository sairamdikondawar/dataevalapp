import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/auth.service'; 

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        console.log("inside intersepter"+req.url.indexOf('basicauth'));

        if (!this.authenticationService.isUserLoggedIn() && req.url.indexOf('basicauth') === -1) {
            console.log(` Inside Interceptor Basic ${window.btoa(this.authenticationService.username + ":" + this.authenticationService.password)}`);
            const authReq = req.clone({
                headers: new HttpHeaders({
                    'Content-Type': 'application/json',
                    'Authorization': `Basic ${window.btoa(this.authenticationService.username + ":" + this.authenticationService.password)}`
                })
            });
            return next.handle(authReq);
        } else {

            console.log(` Inside Interceptor Basic `+req.headers);
            return next.handle(req);
        }
    }
}