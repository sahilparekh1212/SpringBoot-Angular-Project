import { Component, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { AuthService } from "src/app/services/authService/auth.service";
import { TeamService } from "src/app/services/teamService/team.service";
import { TeamWithoutId } from "src/app/utility/class/team-without-id/team-without-id";
import { Team } from "src/app/utility/class/team/team";

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
  teams: Team[] = [];
  similarTeamDetails: TeamWithoutId | undefined;
  showSimilarTeamNameAndGameNameError: boolean = false;
  showSimilarTeamEmailIdError: boolean = false;

  @ViewChild('addTeamForm', { static: true }) addTeamForm: NgForm | undefined;

  constructor(private teamService: TeamService, private authService: AuthService) { }

  ngOnInit(): void {
    this.setTeamDetails();
    this.addTeamForm?.valueChanges?.subscribe((res) => {
      //dynamically assigning toggleSimilarTeamDetailsError to false
      if (this.similarTeamDetails) {
        if ((res.teamName.trim() != this.similarTeamDetails.teamName || res.gameName.trim() != this.similarTeamDetails.gameName)) {
          this.toggleSimilarTeamDetailsError(false, this.showSimilarTeamEmailIdError);
        }
        if (res.emailId.trim() != this.similarTeamDetails.emailId) {
          this.toggleSimilarTeamDetailsError(this.showSimilarTeamNameAndGameNameError, false);
        }
      }
    });
  }

  onSubmit(teamDetails: TeamWithoutId) {
    if (this.teams && this.teams.length > 0) {
      this.addTeam(teamDetails);
    } else {
      this.teamService.getTeams().subscribe((res) => {
        if (res) {
          this.teams = res;
          this.teamService.teams = res;
          this.addTeam(teamDetails);
        } else {
          console.log('Something went wrong -> getTeams() -> res=', res);
        }
      },
        (error) => {
          console.log('Error Occured -> getTeams() -> error=', error);
        });
    }
  }

  addTeam(teamDetails: TeamWithoutId) {
    if (this.isTeamNameAndGameUnique(this.teams, teamDetails)) {
      if (this.isTeamEmailIdUnique(this.teams, teamDetails)) {
        this.teamService.addTeam(teamDetails).subscribe((res) => {
          if (res) {
            this.id = res.id;
            this.isTeamAdded = true;
            this.setTeamDetails();
          } else {
            console.log('Something went wrong -> addTeam() -> res=', res);
          }
          this.showMessage = true;
        },
          (error) => {
            console.log('Error Occured -> addTeam() -> error=', error);
          });
      } else {
        this.toggleSimilarTeamDetailsError(this.showSimilarTeamNameAndGameNameError, true, teamDetails);
      }

    } else {
      this.toggleSimilarTeamDetailsError(true, null, teamDetails);
    }
  }

  getRandomTeamName(): string {
    return 'Team ' + Math.floor(Math.random() * 100).toString();
  }

  getRandomEmailId(): string {
    return 'newEmail' + Math.floor(Math.random() * 100).toString() + '@team.com';
  }

  getUniqueTeamName(teams: Team[]): string {
    let result: string = '';
    if (teams) {
      let randomTeamName = this.getRandomTeamName();
      let isRandomTeamNameUnique = false;
      let teamNameList: string[] = [];

      this.teams.forEach((team) => {
        teamNameList.push(team.teamName);
      });

      while (!isRandomTeamNameUnique) {
        if (teamNameList) {
          if (!teamNameList.includes(randomTeamName)) {
            isRandomTeamNameUnique = true;
            result = randomTeamName;
          } else {
            randomTeamName = this.getRandomTeamName();
          }
        }
      }
    }
    return result;
  }

  getUniqueEmailId(teams: Team[]): string {
    let result = '';
    if (teams) {
      let randomEmailId = this.getRandomEmailId();
      let isRandomEmailIdUnique = false;
      let teamEmailsList: string[] = [];

      teams.forEach((team) => {
        teamEmailsList.push(team.emailId);
      });

      while (!isRandomEmailIdUnique) {
        if (teamEmailsList) {
          if (!teamEmailsList.includes(randomEmailId)) {
            isRandomEmailIdUnique = true;
            result = randomEmailId;
          } else {
            randomEmailId = this.getRandomEmailId();
          }
        }
      }

    }
    return result;
  }

  setTeamDetails() {
    this.teams = this.teamService.teams;
    this.teamDetails.gameName = 'game';
    if (this.teams) {
      this.teamDetails.teamName = this.getUniqueTeamName(this.teams);
      this.teamDetails.emailId = this.getUniqueEmailId(this.teams);
    } else {
      this.teamDetails.teamName = this.getRandomTeamName();
      this.teamDetails.emailId = this.getRandomEmailId();
    }
  }

  isTeamNameAndGameUnique(teams: Team[], input: TeamWithoutId): boolean {
    let result: boolean = false;
    if (teams) {
      let teamsCopy: Team[] = teams;
      let matchedTeam = teamsCopy.find((team) => (team.teamName == input.teamName && team.gameName == input.gameName));

      if (!matchedTeam) {
        result = true;
      }

    }
    return result;
  }

  toggleSimilarTeamDetailsError(showSimilarTeamNameAndGameNameError: boolean, showSimilarTeamEmailIdError?: any, similarTeamDetails?: any) {
    this.similarTeamDetails = similarTeamDetails;
    this.showSimilarTeamNameAndGameNameError = showSimilarTeamNameAndGameNameError;

    if (showSimilarTeamEmailIdError === (true || false)) {
      this.showSimilarTeamEmailIdError = showSimilarTeamEmailIdError;
    }

  }

  isTeamEmailIdUnique(teams: Team[], input: TeamWithoutId) {
    let result: boolean = false;
    if (teams) {
      let teamsCopy: Team[] = teams;
      let matchedTeam = teamsCopy.find((team) => (team.emailId == input.emailId));

      if (!matchedTeam) {
        result = true;
      }

    }
    return result;
  }

}
