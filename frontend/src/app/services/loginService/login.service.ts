import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TeamWithoutId } from 'src/app/utility/class/team-without-id/team-without-id';
import { Team } from 'src/app/utility/class/team/team';
import { User } from 'src/app/utility/class/user/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseURL = 'http://localhost:8080/api/v1/';
  public token: string = "";

  constructor(private httpClient: HttpClient) { }

  login(user: User): Observable<string> {
    const URL = this.baseURL + 'login';
    return this.httpClient.post<string>(URL, user);
  }

}
