import { Component, OnInit } from '@angular/core';
import { TeamService } from '../services/team.service';
import { Team } from '../team';
import { TeamWithoutId } from '../team-without-id';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {
  teams: Team[] = [];
  showDiv=false;

  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    this.getTeams();
  }

  getTeams() {
    this.teamService.getTeams().subscribe((res) => {
      if (res) {
        this.teams = res;
      } else {
        console.log('Something went wrong -> getTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> getTeam() -> error=', error);
      });
  }

  getTeam(id: number) {
    this.teamService.getTeam(id).subscribe((res) => {
      if (res) {
      } else {
        console.log('Something went wrong -> getTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> getTeam() -> error=', error);
      });
  }

  addTeam() {
    let teamWithoutId: TeamWithoutId = new TeamWithoutId();
    teamWithoutId.emailId = 'a@a.com';
    teamWithoutId.gameName = 'game-name';
    teamWithoutId.teamName = 'team-name';

    this.teamService.addTeam(teamWithoutId).subscribe((res) => {
      if (res) {
        this.teams.push(res);
        this.teams.sort();
      } else {
        console.log('Something went wrong -> addTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> addTeam() -> error=', error);
      });
  }

  updateTeam(team: Team) {
    team.emailId = 'new' +  Math.floor(Math.random()*100).toString() +'@updated.com';
    this.teamService.updateTeam(team).subscribe((res) => {
      if (res) {
      } else {
        console.log('Something went wrong -> updateTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> updateTeam() -> error=', error);
      });
  }

  deleteTeam(id: number) {
    this.teamService.deleteTeam(id).subscribe((res) => {
      if (res) {
        this.teams = this.teams.filter((team) => {
          return team.id != id;
        });
      } else {
        console.log('Something went wrong -> deleteTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> deleteTeam() -> error=', error);
      });
  }

  keepFirst() {
    let teamsToBeKept: number = 1;
    this.teamService.keepFirst(teamsToBeKept).subscribe((res:Team[]) => {
      if (res) {
        let idArr: number[] = [];

        res.forEach((res)=>{
          idArr.push(res.id);
        });

        this.teams = this.teams.filter((team)=>{
          return !idArr.includes(team.id);
        });

      } else {
        console.log('Something went wrong -> keepFirstX() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> keepFirstX() -> error=', error);
      });
  }

}

