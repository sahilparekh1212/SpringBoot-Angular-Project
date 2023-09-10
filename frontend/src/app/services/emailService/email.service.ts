import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailInfo } from 'src/app/utility/class/emailInfo/emailInfo';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  baseURL = 'http://localhost:8080/api/v1/';

  constructor(private httpClient: HttpClient) { }

  sendEmail(emailInfo: EmailInfo): Observable<Boolean> {
    let URL: string = this.baseURL + 'sendEmail';
    return this.httpClient.post<Boolean>(URL, emailInfo, this.getAuthRToken());
  }

  getAuthRToken(): object {
    return {
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("Authorization")
      }
    }
  }
}
