import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeamService } from '../services/team.service';
import { Team } from '../utility/class/team/team';

@Component({
  selector: 'app-update-team',
  templateUrl: './update-team.component.html',
  styleUrls: ['./update-team.component.less']
})
export class UpdateTeamComponent implements OnInit {

  team: Team = new Team();
  showMessage: boolean = false;
  isTeamUpdated: boolean = false;

  constructor(private teamService: TeamService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getTeam(this.activatedRoute.snapshot.params['id']);
  }

  onSubmit() {
    this.updateTeam(this.team);
  }

  getTeam(id: number) {
    this.teamService.getTeam(id).subscribe((res) => {
      if (res) {
        this.team = res;
      } else {
        console.log('Something went wrong -> getTeam() -> res=', res);
      }
    },
      (error) => {
        console.log('Error Occured -> getTeam() -> error=', error);
      });
  }

  updateTeam(team: Team) {
    this.teamService.updateTeam(team).subscribe((res) => {
      if (res) {
        this.team = res;
        this.isTeamUpdated = true;
      } else {
        console.log('Something went wrong -> updateTeam() -> res=', res);
      }
      this.showMessage = true;
    },
      (error) => {
        console.log('Error Occured -> updateTeam() -> error=', error);
      });
  }

}
