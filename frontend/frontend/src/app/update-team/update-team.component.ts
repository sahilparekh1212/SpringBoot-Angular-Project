import { Component, OnInit } from '@angular/core';
import { TeamService } from '../services/team.service';
import { Team } from '../utility/class/team/team';

@Component({
  selector: 'app-update-team',
  templateUrl: './update-team.component.html',
  styleUrls: ['./update-team.component.less']
})
export class UpdateTeamComponent implements OnInit {

  team: Team = new Team();

  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    this.team.id=1;
    this.team.teamName='ab';
    this.team.gameName='cd';
    this.team.emailId='e@f.com'
  }

  onSubmit() {
    this.updateTeam(this.team);
  }

  updateTeam(team: Team) {
    // team.emailId = 'new' + Math.floor(Math.random() * 100).toString() + '@updated.com';
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

}
