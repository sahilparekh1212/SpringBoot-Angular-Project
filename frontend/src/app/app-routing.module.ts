import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTeamComponent } from './add-team/add-team.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { UpdateTeamComponent } from './update-team/update-team.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'addTeam', component: AddTeamComponent },
  { path: 'updateTeam/:id', component: UpdateTeamComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: AppComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
