import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TeamService } from './services/teamService/team.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddTeamComponent } from './components/add-team/add-team.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { UpdateTeamComponent } from './components/update-team/update-team.component';
import { AuthService } from './services/authService/auth.service';
import { EmailService } from './services/emailService/email.service';

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
  providers: [AuthService, TeamService, EmailService],
  bootstrap: [AppComponent]
})
export class AppModule { }
