import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TeamService } from './services/team.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddTeamComponent } from './add-team/add-team.component';
import { HomeComponent } from './home/home.component';
import { UpdateTeamComponent } from './update-team/update-team.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/loginService/login.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddTeamComponent,
    UpdateTeamComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [LoginService, TeamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
