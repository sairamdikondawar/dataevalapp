import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { UserFormQuery } from 'src/app/model/common/userformquery.model';
import { CommonService } from 'src/app/services/common.service';
import { PatientCallLogDataSouce } from '../datasoruce/patientcalllog-datasource.model';
import { PatinetCallLogQuery } from '../model/common/patientcalllogquery.model';
import { PatientCallLogService } from '../services/patientcalllog.service';


@Component({
  selector: 'app-manage-patinetlog',
  templateUrl: './manage-patinetlog.component.html',
  styleUrls: ['./manage-patinetlog.component.css']
})
export class ManagePatinetlogComponent implements OnInit {

  currentPageIndex: number = 0;

  searchForm: FormGroup;
  count: number = 0;
  today: Date= new Date();

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('closeFlowConfigModal') closeFlowConfigModal: ElementRef;


  dataSource: PatientCallLogDataSouce;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: PatientCallLogService,
    private modalService: NgbModal,
    private commonService: CommonService, private router: Router,) { 
  }

  // 'callRecordStatus',
  displayedColumns = ['id', 'patientname', 'callType', 'nextappnextMonthAppointmentDatedate', 'remainingtime', 'timeSpentInSession', 'totalTimeSpent', 'actions'];


  ngOnInit() {
    this.searchForm = new FormGroup({ 
      callType: new FormControl(this.today.getMonth()+1),
      patientName: new FormControl(''),
      startDate: new FormControl('')
    })
    this.dataSource = new PatientCallLogDataSouce(this.service);
    this.search();
    this.dataSource.counter$.subscribe((count) => {
      this.count = count;
      console.log('result In count:', this.count);
    });
   
  }

  ngAfterViewInit() {
    this.dataSource.counter$
      .pipe(
        tap((count: any) => {
          this.paginator.length = count;
        })
      )
      .subscribe();
    this.paginator.page
      .pipe(
        tap(() => this.search())
      )
      .subscribe();
  }

  lists() {

    this.dataSource.query = new PatinetCallLogQuery();
    if (this.searchForm.controls.uName.value != null)
      this.dataSource.query.patientName = this.searchForm.controls.patientName.value;
    this.dataSource.query.startDate = this.searchForm.controls.startDate.value;
    this.dataSource.query.callType = this.searchForm.controls.callType.value != null ? this.searchForm.controls.callType.value : "";
    this.dataSource.list(this.paginator.pageIndex, this.paginator.pageSize);
  }



  view(formId: number) {
    this.router.navigate(['/userformdetails/' + formId]
    );
  }
  onPaginateChange(event: any) {
    this.currentPageIndex = event.pageIndex == 0 ? 0 : (event.pageIndex) * 10;
  }
  search() {
    if (this.searchForm.controls.patientName.value != null)
      this.dataSource.query.patientName = this.searchForm.controls.patientName.value;
    this.dataSource.query.startDate = this.searchForm.controls.startDate.value;
    this.dataSource.query.callType = this.searchForm.controls.callType.value != null ? this.searchForm.controls.callType.value : "";
    this.dataSource.list();
  }
  resetSearch() {
    this.dataSource.query = new PatinetCallLogQuery();
    this.dataSource.query.patientName = '';
    this.dataSource.query.callType = "";
    this.dataSource.query.startDate = null;
    this.searchForm.controls.startDate.setValue("");
    
    this.searchForm.reset();
    this.searchForm.controls.callType.setValue(this.today.getMonth()+1);
    this.search();
    // alert(this.searchForm.controls.sname.value +  "  "+this.dataSource.query.sectionId)
  }

  calllogrecord(patientId: number) {
    this.router.navigate(['/patientcalllog/' + patientId]
    );
  }

  viewcalllogrecord(patientId: number) {
    this.router.navigate(['/viewpatientcalllog/' + patientId]
    );
  }

  addcalllog() {
    this.router.navigate(['/selectpatient']
    );

  }

  checkDisabled(row:any){
    console.log(row.remaingTime/60);
    return row.remaingTime/60 != 0;
  }


}
