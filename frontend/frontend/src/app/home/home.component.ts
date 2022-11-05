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
  response: any;
  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
  }

  getTeams() {
    this.teamService.getTeams().subscribe((res) => {
      if (res) {
        this.response = res;
        console.log(res);
      } else {
        console.log('Something went wrong -> getTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> getTeam()');
      });
  }

  getTeam() {
    this.teamService.getTeam(20).subscribe((res) => {
      if (res) {
        this.response = res;
        console.log(res);
      } else {
        console.log('Something went wrong -> getTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> getTeam()');
      });
  }

  addTeam() {
    let teamWithoutId: TeamWithoutId = new TeamWithoutId();
    teamWithoutId.emailId = 'a@a.com';
    teamWithoutId.gameName = 'game-name';
    teamWithoutId.teamName = 'team-name';

    this.teamService.addTeam(teamWithoutId).subscribe((res) => {
      if (res) {
        this.response = res;
        console.log(res);
      } else {
        console.log('Something went wrong -> addTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> addTeam()');
      });
  }

  updateTeam() {
    let input: Team = this.response;
    input.emailId = 'updated@updated.com';
    this.teamService.updateTeam(input).subscribe((res) => {
      if (res) {
        this.response = res;
        console.log(res);
      } else {
        console.log('Something went wrong -> updateTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> updateTeam()');
      });
  }

  deleteTeam() {
    this.teamService.deleteTeam(this.response.id).subscribe((res) => {
      if (res) {
        this.response = res;
        console.log(res);
      } else {
        console.log('Something went wrong -> deleteTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> deleteTeam()');
      });
  }

  keepFirst() {
    let teamsToBeKept: number= 1;
    this.teamService.keepFirst(teamsToBeKept).subscribe((res) => {
      if (res) {
        this.response = res;
        console.log(res);
      } else {
        console.log('Something went wrong -> keepFirstX() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> keepFirstX()');
      });
  }

}

