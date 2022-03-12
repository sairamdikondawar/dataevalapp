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
  admin:boolean=false; 
  nurse:boolean=false;
  doctor:boolean=false


  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,private observer: BreakpointObserver) { }

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
     
    this.authenticationService.running.subscribe((isRunning) => {
      this.isLoggedIn = isRunning;
      

       console.log('logged In :', this.isLoggedIn);
   });
    console.log('menu ->' + this.isLoggedIn); 

    this.authenticationService.userName.subscribe((userName) => {
      this.userName = userName;
      
       console.log('logged In User:', this.userName);
   });

   this.authenticationService.firstName.subscribe((firstName) => {
     
    
     console.log('logged In User FirstName:', firstName);
 });

   this.authenticationService.userType.subscribe((userType) => {
    this.userType = userType;
    this.admin=false;
    if(userType === 'admin')
      {
        this.admin=true;
       
      }else
      if(userType === 'nurse')
      {
        this.nurse=true;
      }else 
      if(userType === 'doctor')
      {
        this.doctor=true;
      }
     console.log(this.admin + 'logged In UserType :', this.userType);

     console.log(this.nurse + 'logged In UserType :', this.userType);

     console.log(this.doctor + 'logged In UserType :', this.userType);
 });
  }

  handleLogout() {

    this.sidenav.close(); 
    this.authenticationService.logout();
    this.view("/")
  }

  ngAfterViewInit() {
    
      this.sidenav.close(); 
      

      
  }

  view(url: string) {
    // alert(url);
    this.router.navigate([url]);

  }

  showMenu()
  {
    this.sidenav.mode='side';
    if(this.sidenav.opened){
      this.sidenav.close(); 
    }
   else {
      this.sidenav.open(); 
    }
   
  }
}
