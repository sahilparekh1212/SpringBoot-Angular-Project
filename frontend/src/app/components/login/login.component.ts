import { Component, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { LoginService } from "src/app/services/loginService/login.service";
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

  constructor(private loginService: LoginService, private teamService: TeamService) { }

  ngOnInit(): void {

  }

  onSubmit(userDetails: User) {
    if (userDetails.username && userDetails.password) {

      this.loginService.login(userDetails).subscribe((res: ResponseObj) => {
        console.log("res", res);
        if (res && res.data) {
          localStorage.setItem("Authorization", res.data);
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
