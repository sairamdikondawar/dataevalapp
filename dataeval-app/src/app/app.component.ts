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
  }

  view(url: string) {
    this.router.navigate([url]
    );

  }
}
