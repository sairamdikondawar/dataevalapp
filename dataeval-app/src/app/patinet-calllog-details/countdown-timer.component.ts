import { Component, OnDestroy } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-countdown-timer',
  template: ''
})
export class CountdownTimerComponent implements OnDestroy {

  intervalId = 0;
  message = '';
  seconds = 0;

  secondTrackSubject = new BehaviorSubject(0);
   secondsTrack=this.secondTrackSubject.asObservable();;

  ngOnDestroy() { this.clearTimer(); }

  start() { this.countDown(); }
  stop()  {
    this.clearTimer();
    this.message = `Holding at T-${this.seconds} seconds`;
  }

  private clearTimer() { clearInterval(this.intervalId); }

  private countDown() {
    this.clearTimer();
    this.intervalId = window.setInterval(() => {
      this.seconds += 1;
      this.setSecondsTrack(this.seconds);
      if (this.seconds === 20*1000) {
        this.message = 'Blast off!';
      } else {
        if (this.seconds < 0) { this.seconds = 10; } // reset
        this.message = `T-${this.seconds} seconds and counting`;
      }
    }, 1000);
  }

  setSecondsTrack= (value : number) => {
    this.secondTrackSubject.next(value);
  }
}
