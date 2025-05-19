import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Team } from 'src/app/utility/class/team/team';
import { TeamWithoutId } from 'src/app/utility/class/team-without-id/team-without-id';
import { Constants } from 'src/app/utility/Constants';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  teams: Team[] = [];

  constructor(private readonly httpClient: HttpClient) { }

  getTeams(): Observable<Team[]> {
    return this.httpClient.get<Team[]>(Constants.URL_GET_TEAMS, this.getAuthRToken());
  }

  getTeam(id: number): Observable<any> {
    const URL = Constants.URL_GET_TEAM + id;
    return this.httpClient.get<any>(URL, this.getAuthRToken());
  }

  addTeam(teamWithoutId: TeamWithoutId): Observable<Team> {
    return this.httpClient.post<Team>(Constants.URL_ADD_TEAM, teamWithoutId, this.getAuthRToken());
  }

  updateTeam(team: Team): Observable<Team> {
    const URL = Constants.URL_UPDATE_TEAM + team.id;
    return this.httpClient.put<Team>(URL, team, this.getAuthRToken());
  }

  deleteTeam(id: number): Observable<Team> {
    const URL = Constants.URL_DELETE_TEAM + id;
    return this.httpClient.delete<Team>(URL, this.getAuthRToken());
  }

  keepFirst(teamsToBeKept: number): Observable<Team[]> {
    const URL = Constants.URL_KEEP_FIRST + teamsToBeKept;
    return this.httpClient.delete<Team[]>(URL, this.getAuthRToken());
  }

  getAuthRToken(): object {
    return {
      headers: {
        "Authorization": Constants.AUTH_PREFIX + localStorage.getItem(Constants.AUTHORIZATION)
      }
    }
  }

}
