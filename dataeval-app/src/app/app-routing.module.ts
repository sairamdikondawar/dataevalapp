import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlowconfigComponent } from './admin/flowconfig/flowconfig.component';
import { LoginComponent } from './auth/login/login.component';
import { ManageuserComponent } from './admin/manageuser/manageuser.component';
import { QuestionComponent } from './admin/question/question.component';
import { MultiformComponent } from './user/multiform/multiform.component';

const routes: Routes = [
  {path: 'flowconfig', component: FlowconfigComponent},
  {path: 'question', component: QuestionComponent},
  {path: 'login', component: LoginComponent},
  {path: '', component: LoginComponent},
  {path: 'logout', component: LoginComponent},
  {path : 'user', component : ManageuserComponent},
  {path : 'patient',component : MultiformComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
