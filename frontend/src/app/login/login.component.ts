import { Component, ViewChild } from '@angular/core';
import { User } from '../utility/class/user/user';
import { NgForm } from '@angular/forms';
import { LoginService } from '../services/loginService/login.service';

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

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {

  }

  onSubmit(userDetails: User) {
    if (userDetails.username && userDetails.password) {

      this.loginService.login(userDetails).subscribe((res: string | undefined) => {
        if (res) {
          this.loginService.token = res;
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
