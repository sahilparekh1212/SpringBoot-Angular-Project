import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/authService/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {

  userActionText: string = "";
  userActionURL: string = "";

  constructor(private authService: AuthService) {

  }

  ngOnInit(): void {
    this.authService.isUserAuthnticated.subscribe((isAuthenticated) => {
      this.userActionText = isAuthenticated && localStorage.getItem("Authorization") ? "Logout" : "Login";
      this.setUserActionURL();
    });
  }

  setUserActionURL(): void {
    this.userActionURL = "/" + this.userActionText.toLowerCase();
  }

}
