import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlowconfigComponent } from './admin/flowconfig/flowconfig.component';
import { LoginComponent } from './auth/login/login.component';
import { ManageuserComponent } from './admin/manageuser/manageuser.component';
import { QuestionComponent } from './admin/question/question.component';
import { MultiformComponent } from './user/multiform/multiform.component';
import { UserformsComponent } from './admin/userforms/userforms.component';
import { UserformdetailsComponent } from './admin/userformdetails/userformdetails.component';
import { PageconfigComponent } from './admin/pageconfig/pageconfig.component';
import { SectionsComponent } from './admin/sections/sections.component';
import { ManagePatinetlogComponent } from './manage-patinetlog/manage-patinetlog.component';
import { PatinetCalllogDetailsComponent } from './patinet-calllog-details/patinet-calllog-details.component';
import { SelectPatientComponent } from './patinet-calllog-details/select-patient.component';
import { ViewPatinetCalllogDetailsComponent } from './patinet-calllog-details/view-patinet-calllog-details.component';
import { ViewPatinetlogComponent } from './manage-patinetlog/view-patinetlog.component';
import { ChangePasswordComponent } from './admin/change-password/change-password.component';

const routes: Routes = [
  { path: 'flowconfig', component: FlowconfigComponent },
  { path: 'question', component: QuestionComponent },
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'logout', component: LoginComponent },
  { path: 'user', component: ManageuserComponent },
  { path: 'patient', component: MultiformComponent },
  { path: 'patient/:role', component: MultiformComponent },
  { path: 'userforms', component: UserformsComponent},
  { path: 'steps', component: PageconfigComponent},
  { path: 'sections', component: SectionsComponent},
  { path: 'userformdetails/:id', component: UserformdetailsComponent},
  {path: 'managepatient', component: ManagePatinetlogComponent},
  {
    path: 'patientcalllog', component: PatinetCalllogDetailsComponent
  },
  {
    path: 'patientcalllog/:id', component: PatinetCalllogDetailsComponent
  },
  {
    path:'selectpatient',component:SelectPatientComponent
  },{
    path: 'viewpatientcalllog/:id', component: ViewPatinetCalllogDetailsComponent
  },
  {
    path: 'viewpatientcalllogsearch', component: ViewPatinetlogComponent
  },{
    path: 'changepassword' , component: ChangePasswordComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
