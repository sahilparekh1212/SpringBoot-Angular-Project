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
  showMessage: boolean = false;
  isTeamAdded: boolean = false;
  id: number | undefined;

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
        this.id = res.id;
        this.isTeamAdded = true;
      } else {
        console.log('Something went wrong -> addTeam() -> res=', res);
      }
      this.showMessage = true;
    },
      (error) => {
        console.log('Error Occured -> addTeam() -> error=', error);
      });
  }


}
