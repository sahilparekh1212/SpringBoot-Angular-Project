import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { ResponseObj } from 'src/app/utility/class/ResponseObj/ResponseObj';
import { TeamWithoutId } from 'src/app/utility/class/team-without-id/team-without-id';
import { Team } from 'src/app/utility/class/team/team';
import { User } from 'src/app/utility/class/user/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseURL = 'http://localhost:8080/api/v1/';
  isUserAuthnticated: BehaviorSubject<boolean> = new BehaviorSubject(false);
  constructor(private httpClient: HttpClient, private router: Router) { }

  login(user: User): Observable<ResponseObj> {
    const URL = this.baseURL + 'login';
    return this.httpClient.post<ResponseObj>(URL, user);
  }

  logout() {
    localStorage.removeItem("Authorization");
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
    if (localStorage.getItem("Authorization") && this.getIsUserAuthnticated()) {
      this.router.navigateByUrl("/logout");
      this.logout();
    } else {
      this.router.navigateByUrl("/login");
    }
  }

}
