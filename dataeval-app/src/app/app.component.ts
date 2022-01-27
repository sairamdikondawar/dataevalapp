import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { ActivatedRoute, Router } from '@angular/router';
import { delay } from 'rxjs/operators';
import { AuthenticationService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'dataeval-app';

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  isLoggedIn = false;
  userName:string;
  userType:string;
  admin:boolean;
  opened:boolean=false;


  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,private observer: BreakpointObserver) { }

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
     
    this.authenticationService.running.subscribe((isRunning) => {
      this.isLoggedIn = isRunning;
     
     if(!this.isLoggedIn)
     {
      this.opened=false;
      // this.sidenav.close();
     }else{
      this.opened=true;
      // this.sidenav.open();
     }

       console.log('logged In :', this.isLoggedIn);
   });
    console.log('menu ->' + this.isLoggedIn); 

    this.authenticationService.userName.subscribe((userName) => {
      this.userName = userName;
      
       console.log('logged In User:', this.userName);
   });

   this.authenticationService.userType.subscribe((userType) => {
    this.userType = userType;
    if(userType == 'admin')
      {
        this.admin=true;
      }
     console.log('logged In UserType :', this.userType);
 });
  }

  handleLogout() {

   
    this.authenticationService.logout();
    this.view("/")
  }

  ngAfterViewInit() {
    // alert("test");
    this.observer
      .observe(['(max-width: 800px;)'])
      .pipe(delay(1))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
         
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
           
        }
      });
     
      if(!this.isLoggedIn)
     {
      this.opened=false;
      // alert(this.isLoggedIn)
      this.sidenav.mode='over';
      this.sidenav.close();
     }else{
      this.opened=true;
      // this.sidenav.open();
     }

      
  }

  view(url: string) {
    this.router.navigate([url]
    );

  }
}
