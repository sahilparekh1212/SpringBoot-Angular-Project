import { Component, OnInit } from '@angular/core';
import { TeamService } from '../services/team.service';
import { TeamWithoutId } from '../utility/class/team-without-id/team-without-id';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.less']
})
export class AddTeamComponent implements OnInit {

  teamDetails: TeamWithoutId = new TeamWithoutId();

  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    this.teamDetails.teamName = 'team name';
    this.teamDetails.gameName = 'game name';
    this.teamDetails.emailId = 'random' +  Math.floor(Math.random()*100).toString() +'@team.com';
  }

  onSubmit(){
    this.addTeam(this.teamDetails);
  }

  addTeam(teamDetails: TeamWithoutId) {
    this.teamService.addTeam(teamDetails).subscribe((res) => {
      if (res) {

      } else {
        console.log('Something went wrong -> addTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> addTeam() -> error=', error);
      });
  }


}
