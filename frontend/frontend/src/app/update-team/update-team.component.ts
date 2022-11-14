import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  updateTeamFormGroup: FormGroup = new FormGroup({
    teamName: new FormControl(this.team.teamName, Validators.compose([Validators.required, Validators.minLength(3)])),
    gameName: new FormControl(this.team.gameName, Validators.compose([Validators.required, Validators.minLength(4)])),
    emailId: new FormControl(this.team.emailId, Validators.compose([Validators.required]))
  });

  constructor(private teamService: TeamService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getTeam(this.activatedRoute.snapshot.params['id']);
  }

  onSubmit() {
    if (this.updateTeamFormGroup.valid) {
      this.updateTeam(this.team);
    } else {
      console.log('Form is invalid');
    }
  }

  getTeam(id: number) {
    this.teamService.getTeam(id).subscribe((res) => {
      if (res) {
        this.team = res;
        this.defaultFormValues(res);
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

  defaultFormValues(team: Team) {
    this.updateTeamFormGroup.patchValue({
      teamName: team.teamName,
      gameName: team.gameName,
      emailId: team.emailId
    });
  }

  get teamName() {
    return this.updateTeamFormGroup.get('teamName');
  }

  get gameName() {
    return this.updateTeamFormGroup.get('gameName');
  }

  get emailId() {
    return this.updateTeamFormGroup.get('emailId');
  }

  clearForm() {
    let team = new Team();
    team.id = this.team.id;
    team.teamName = '';
    team.gameName = '';
    team.emailId = '';
    this.defaultFormValues(team);
  }

}
