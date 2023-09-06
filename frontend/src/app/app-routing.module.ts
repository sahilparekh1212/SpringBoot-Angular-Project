import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTeamComponent } from './components/add-team/add-team.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { UpdateTeamComponent } from './components/update-team/update-team.component';
import { authGuardFn } from './utility/functions/authGuardFn';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LoginComponent, canActivate: [authGuardFn] },
  { path: 'home', component: HomeComponent, canActivate: [authGuardFn] },
  { path: 'addTeam', component: AddTeamComponent, canActivate: [authGuardFn] },
  { path: 'updateTeam/:id', component: UpdateTeamComponent, canActivate: [authGuardFn] },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: AppComponent, canActivate: [authGuardFn] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
