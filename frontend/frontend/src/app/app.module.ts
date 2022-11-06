import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TeamService } from './services/team.service';
import { FormsModule } from '@angular/forms';
import { AddTeamComponent } from './add-team/add-team.component';
import { HomeComponent } from './home/home.component';
import { UpdateTeamComponent } from './update-team/update-team.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddTeamComponent,
    UpdateTeamComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [TeamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
