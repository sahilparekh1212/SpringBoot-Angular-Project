import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailInfo } from 'src/app/utility/class/emailInfo/emailInfo';
import { Constants } from 'src/app/utility/Constants';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private httpClient: HttpClient) { }

  sendEmail(emailInfo: EmailInfo): Observable<Boolean> {
    return this.httpClient.post<Boolean>(Constants.URL_SEND_EMAIL, emailInfo, this.getAuthRToken());
  }

  getAuthRToken(): object {
    return {
      headers: {
        "Authorization": Constants.AUTH_PREFIX + localStorage.getItem(Constants.AUTHORIZATION)
      }
    }
  }
}
