package com.backend.utility;

public class TeamWithoutId {

	private String teamName;

	private String gameName;

	private String emailId;

	public TeamWithoutId() {
	}

	public TeamWithoutId(String teamName, String gameName, String emailId) {
		super();
		this.teamName = teamName;
		this.gameName = gameName;
		this.emailId = emailId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getemailId() {
		return emailId;
	}

	public void setemailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", gameName=" + gameName + ", emailId=" + emailId + "]";
	}

}
