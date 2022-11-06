import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Team } from '../utility/class/team/team';
import { TeamWithoutId } from '../utility/class/team-without-id/team-without-id';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  baseURL = 'http://localhost:8080/api/v1/';

  constructor(private httpClient: HttpClient) { }

  getTeams(): Observable<Team[]> {
    const URL = this.baseURL + 'getTeams';
    return this.httpClient.get<Team[]>(URL);
  }

  getTeam(id: number): Observable<any> {
    const URL = this.baseURL + 'getTeams/' + id;
    return this.httpClient.get<any>(URL);
  }

  addTeam(teamWithoutId: TeamWithoutId): Observable<Team> {
    const URL = this.baseURL + 'addTeam';
    return this.httpClient.post<Team>(URL, teamWithoutId);
  }

  updateTeam(team: Team): Observable<Team>{
    const URL = this.baseURL + 'updateTeam/' + team.id;
    return this.httpClient.put<Team>(URL,team);
  }

  deleteTeam(id: number): Observable<Team>{
    const URL = this.baseURL + 'deleteTeam/' + id;
    return this.httpClient.delete<Team>(URL);
  }

  keepFirst(teamsToBeKept:number): Observable<Team[]>{
    const URL = this.baseURL + 'keepFirst/' + teamsToBeKept;
    return this.httpClient.delete<Team[]>(URL);
  }

}
