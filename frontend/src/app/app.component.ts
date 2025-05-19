import { Component, OnInit } from '@angular/core';
import { AuthService } from "src/app/services/authService/auth.service";
import { Constants } from 'src/app/utility/Constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {

  userActionText: string = "";
  userActionURL: string = "";

  constructor(private readonly authService: AuthService) {

  }

  ngOnInit(): void {
    this.authService.isUserAuthnticated.subscribe((isAuthenticated) => {
      this.userActionText = isAuthenticated && localStorage.getItem(Constants.AUTHORIZATION) ? "Logout" : "Login";
      this.setUserActionURL();
    });
  }

  setUserActionURL(): void {
    this.userActionURL = "/" + this.userActionText.toLowerCase();
  }

}
