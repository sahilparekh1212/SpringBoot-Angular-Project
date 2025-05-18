import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { ResponseObj } from 'src/app/utility/class/ResponseObj/ResponseObj';
import { User } from 'src/app/utility/class/user/user';
import { Constants } from 'src/app/utility/Constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isUserAuthnticated: BehaviorSubject<boolean> = new BehaviorSubject(false);
  constructor(private httpClient: HttpClient, private router: Router) { }

  login(user: User): Observable<ResponseObj> {
    return this.httpClient.post<ResponseObj>(Constants.URL_LOGIN, user);
  }

  logout() {
    localStorage.removeItem(Constants.AUTHORIZATION);
    this.router.navigateByUrl("/logout");
    this.setIsUserAuthnticated(false);
  }

  setIsUserAuthnticated(val: boolean): void {
    this.isUserAuthnticated.next(val);
  }

  getIsUserAuthnticated(): boolean {
    return this.isUserAuthnticated.getValue();
  }

  toggleIsUserAuthnticated(): void {
    this.setIsUserAuthnticated(!this.getIsUserAuthnticated());
  }

  navigateUserBasedOnAuthStatus() {
    if (localStorage.getItem(Constants.AUTHORIZATION) && this.getIsUserAuthnticated()) {
      this.router.navigateByUrl("/logout");
      this.logout();
    } else {
      this.router.navigateByUrl("/login");
    }
  }

}
