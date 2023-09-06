import { Component, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/authService/auth.service";
import { TeamService } from "src/app/services/teamService/team.service";
import { ResponseObj } from "src/app/utility/class/ResponseObj/ResponseObj";
import { User } from "src/app/utility/class/user/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent {
  showMessage: boolean = false;
  user: User = new User();
  showLoginFailedError: boolean = false;

  @ViewChild('loginUserForm', { static: true }) loginUserForm: NgForm | undefined;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    if (this.authService.getIsUserAuthnticated() || localStorage.getItem("Authorization")) {
      this.authService.logout();
    }
  }

  onSubmit(userDetails: User) {
    if (userDetails.username && userDetails.password) {

      this.authService.login(userDetails).subscribe((res: ResponseObj) => {
        if (res?.data) {
          localStorage.setItem("Authorization", res.data);
          this.router.navigateByUrl("/home");
          this.authService.setIsUserAuthnticated(true);
        } else {
          this.showLoginError();
          console.log('Something went wrong -> login() -> res=', res);
        }
      },
        (error: any) => {
          this.showLoginError();
          console.log('Error Occured -> login() -> error=', error);
        });
    }
  }

  showLoginError() {
    this.showLoginFailedError = true;
  }
}
