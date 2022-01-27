import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders, JsonpClientBackend } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/auth.service'; 

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // console.log(this.authenticationService.username+"inside intersepter Test "+JSON.stringify(this.authenticationService.username));

        if (this.authenticationService.isUserLoggedIn() && req.url.indexOf('basicauth') === -1) {
            console.log(` Inside Interceptor Basic ${window.btoa(this.authenticationService.username + ":" + this.authenticationService.password)}`);
            const authReq = req.clone({
                headers: new HttpHeaders({
                    'Content-Type': 'application/json',
                    'Authorization': `Basic ${window.btoa(this.authenticationService.getLoggedInUserName() + ":" + this.authenticationService.getPassword())}`
                })
            });
            return next.handle(authReq);
        } else {

            console.log(` Inside Interceptor Basic `+JSON.stringify(req.headers));
            return next.handle(req);
        }
    }
}