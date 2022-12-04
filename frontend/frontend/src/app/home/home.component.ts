import { Component, OnInit } from '@angular/core';
import { TeamService } from '../services/team.service';
import { Team } from '../utility/class/team/team';
import { TeamWithoutId } from '../utility/class/team-without-id/team-without-id';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {
  teams: Team[] = [];
  showDiv = false;

  constructor(private teamService: TeamService, private router: Router) { }

  ngOnInit(): void {
    this.getTeams();
  }

  getTeams() {
    this.teamService.getTeams().subscribe((res) => {
      if (res) {
        this.teams = res;
        this.teamService.teams = res;
      } else {
        console.log('Something went wrong -> getTeams() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> getTeams() -> error=', error);
      });
  }

  updateTeam(id: number) {
    this.router.navigate(['updateTeam', id]);
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
    this.teamService.keepFirst(teamsToBeKept).subscribe((res: Team[]) => {
      if (res) {
        let idArr: number[] = [];

        res.forEach((res) => {
          idArr.push(res.id);
        });

        this.teams = this.teams.filter((team) => {
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

