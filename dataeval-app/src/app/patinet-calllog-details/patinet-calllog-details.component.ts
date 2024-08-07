import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AlertService } from '../alert';
import { Lookup } from '../model/lookup.model';
import { PatientCallLog } from '../model/patientcallog.model';
import { User } from '../model/user.model';
import { AuthenticationService } from '../services/auth.service';
import { CommonService } from '../services/common.service';
import { PatientCallLogService } from '../services/patientcalllog.service';
import { RoleService } from '../services/role.service';
import { UserService } from '../services/user.service';
import { CountdownTimerComponent } from './countdown-timer.component';

@Component({
  selector: 'app-patinet-calllog-details',
  templateUrl: './patinet-calllog-details.component.html',
  styleUrls: ['./patinet-calllog-details.component.css']
})
export class PatinetCalllogDetailsComponent implements AfterViewInit, OnInit {

  @ViewChild(CountdownTimerComponent)
  private timerComponent!: CountdownTimerComponent;
  firstName: string;
  lastName: string;
  createForm: FormGroup;
  patientFirstName: string;
  patientLastName: string;
  remaingTime: number;
  id: string;
  visatedDate: string;
  visatedDateInDate:Date;
  maxVisitDate:string;
  today = new Date().toISOString().slice(0, 16);
  nowDate=new Date();
  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  seconds() { return 0; }
  constructor(private service: PatientCallLogService, private userService: UserService, private roleService: RoleService,
    private modalService: NgbModal,
    private commonService: CommonService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute,
    private alertService: AlertService) { 
  } 
  model = new PatientCallLog();
  patientModel = new User();
  ngAfterViewInit() { 
    this.model = new PatientCallLog();
  }

  ngOnInit() {

    this.id = this.route.snapshot.paramMap.get('id');
    this.visatedDate = this.today;
    let nowDate1=new Date();
    nowDate1.setMonth(this.nowDate.getMonth())
    this.maxVisitDate= new Date(nowDate1.getFullYear(), nowDate1.getMonth()+1, 1).toISOString().slice(0, 16);
    // alert(this.maxVisitDate + " - "+this.visatedDate)
    this.createForm = new FormGroup({
      callType: new FormControl('Patient Call'),
      patientFName: new FormControl(this.patientFirstName),
      patientLName: new FormControl(this.patientLastName),
      staffFName: new FormControl(this.firstName),
      staffLName: new FormControl(this.lastName),
      hCondition: new FormControl(this.patientFirstName),
      mtOutCome: new FormControl(''),
      mSymptoms: new FormControl(''),
      addNotes: new FormControl(''),
      nextAptDate: new FormControl('',[Validators.required]),
    });
    this.userService.getpatient(this.id).pipe(
      catchError(() => of([]))
    )
      .subscribe((result: User) => {
        console.log("inside success :: " + result);
        this.patientFirstName = result.firstName;
        this.patientLastName = result.lastName;
        console.log('logged In patient First Name:', this.patientFirstName);
        console.log('logged In patient Last Name:', this.patientLastName);
        // this.setTestData(true);
        this.authenticationService.firstName.subscribe((firstName) => {
          this.firstName = firstName; 
          console.log('logged In User First Name:', this.firstName);
        });

        this.authenticationService.lastName.subscribe((lastName) => {
          this.lastName = lastName;
          console.log('logged In User lastName :', this.lastName);
        });

        this.timerComponent.secondsTrack.subscribe((timeInSeconds) => {
           if(timeInSeconds == 900)
            {
              alert(" 15 Mins is completed");
            } ;

            if(timeInSeconds >= 1200)
            {
              // alert(" 15 Mins is completed"); nowDate
              var dateTemp = new Date();
              var firstDay= new Date(this.visatedDateInDate.getFullYear(), this.visatedDateInDate.getMonth()+1, 2)
              this.visatedDate=firstDay.toISOString().slice(0, 16);
              this.maxVisitDate= new Date(firstDay.getFullYear(), firstDay.getMonth()+2, 1).toISOString().slice(0, 16);
            } ;

        }); 
        this.createForm.patchValue({
          patientFName: (this.patientFirstName),
          patientLName: (this.patientLastName),
          staffFName: (this.firstName),
          staffLName: (this.lastName),
           

        });
        this.service.getByPatient(this.id)
          .subscribe((response: PatientCallLog) => {
            // alert(response.nextMonthAppointmentDate);
            this.createForm.patchValue({
              hCondition:response.healthConditionsToDiscuss,
              mtOutCome:response.measurableTreatmentOutcome,
              mSymptoms:response.managingSymptoms,
              addNotes:response.additionalNotes 
    
            });
            this.visatedDateInDate=new Date(response.nextMonthAppointmentDate.toString());
            this.maxVisitDate=new Date(this.visatedDateInDate.getFullYear(), this.visatedDateInDate.getMonth()+1, 1).toISOString().slice(0, 16);
            this.visatedDate = new Date(response.nextMonthAppointmentDate).toISOString().slice(0, 16);
            // alert(this.maxVisitDate);
            setTimeout(() => this.seconds = () => this.timerComponent.seconds, 0);
            this.timerComponent.seconds =   response.totalTimeSpent;
            this.start();

          },
            (error) => {
              console.log('error', error)
              setTimeout(() => this.seconds = () => this.timerComponent.seconds, 0);
              this.timerComponent.seconds = 0;
              this.start();
            }); 
      }
      );
  }

  createNewForm(): FormGroup {
    return new FormGroup({
      callType: new FormControl('Patient Call'),
      patientFName: new FormControl(this.patientFirstName, [Validators.required]),
      patientLName: new FormControl(this.patientLastName),
      staffFName: new FormControl(this.firstName, [Validators.required]),
      staffLName: new FormControl(this.lastName),
      hCondition: new FormControl(this.patientFirstName),
      mtOutCome: new FormControl(''),
      mSymptoms: new FormControl(''),
      addNotes: new FormControl(''),
      nextAptDate: new FormControl([Validators.required]),
    });
  }

  save() {
    console.log('inside submit');
    this.patientModel = new User();
    this.patientModel.id = parseInt(this.id);
    this.model.user = this.patientModel;
    this.model.careTeamMemberFirstName = this.createForm.controls.staffFName.value;

    this.model.careTeamMemberLastName = this.createForm.controls.staffLName.value;

    this.model.callType = this.createForm.controls.callType.value;

    this.model.healthConditionsToDiscuss = this.createForm.controls.hCondition.value;

    this.model.measurableTreatmentOutcome = this.createForm.controls.mtOutCome.value;

    this.model.managingSymptoms = this.createForm.controls.mSymptoms.value;

    this.model.additionalNotes = this.createForm.controls.addNotes.value;

    this.model.nextMonthAppointmentDate = this.createForm.controls.nextAptDate.value;
    this.model.totalTimeSpent = this.timerComponent.seconds;

    this.service.create(this.model)
      .pipe(
        catchError(() => of([]))

      )
      .subscribe((result) => {
        console.log(result + " result");
        // this.dataSource.list();
        this.timerComponent.seconds = 0;
        this.stop();
        // alert("Success");
        this.alertService.success("Success", this.options);
        this.router.navigate(['/managepatient']);
      }
      );
    this.resetCreateForm();

    this.modalService.dismissAll();

  }



  get sName() { return this.createForm.get('sName'); }

  get fName() { return this.createForm.get('fName'); }


  get lName() { return this.createForm.get('lName'); }

  resetCreateForm() {
    this.createForm.reset();
    this.createForm = new FormGroup({
      callType: new FormControl('Patient Call', [Validators.required]),
      patientFName: new FormControl(this.patientFirstName),
      patientLName: new FormControl(this.patientLastName),
      staffFName: new FormControl(this.firstName),
      staffLName: new FormControl(this.lastName),
      hCondition: new FormControl(this.patientFirstName),
      mtOutCome: new FormControl(''),
      mSymptoms: new FormControl(''),
      addNotes: new FormControl(''),
      nextAptDate: new FormControl([Validators.required]),
    });
  }

  start() { this.timerComponent.start(); }
  stop() { this.timerComponent.stop(); }

  selectPatient() {
    this.router.navigate(['/selectpatient']);
  }

  navigateBack() {
    this.router.navigate(['/managepatient']);
  }



}
