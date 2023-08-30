package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "game_name")
	private String gameName;

	@Column(name = "email_id")
	private String emailId;

	public Team() {
	}

	public Team(String teamName, String gameName, String emailId) {
		super();
		this.teamName = teamName;
		this.gameName = gameName;
		this.emailId = emailId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "Team [id=" + id + ", teamName=" + teamName + ", gameName=" + gameName + ", emailId=" + emailId + "]";
	}

}
