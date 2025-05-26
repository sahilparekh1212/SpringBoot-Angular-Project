package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.util.Constants;

@Entity
@Table(name = Constants.ENTITY_TEAMS, schema = Constants.SCHEMA)
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

	public Team(Long id2, String teamName2, String gameName2, String getemailId) {
		this.id = id2;
		this.teamName = teamName2;
		this.gameName = gameName2;
		this.emailId = getemailId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", gameName=" + gameName + ", emailId=" + emailId + "]";
	}

}
