package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Team;
import com.backend.service.TeamService;
import com.backend.utility.TeamWithoutId;

@RestController
@RequestMapping(value = "/api/v1/")
public class TeamController {

	@Autowired
	private TeamService teamService;

	Logger logger = LogManager.getLogger(TeamController.class);

	@PreAuthorize("hasAuthority('ROLE_user')")
	@GetMapping(value = "getTeams", produces = { "application/json" })
	public ResponseEntity<List<Team>> getTeams() {
		final String LOGEER_PREFIX = "In getTeams:";
		List<Team> teams = new ArrayList<>();
		try {
			teams = teamService.findAllTeams();
		} catch (Exception e) {
			logger.error("{} Error occured:: message={}, stackTrace={}", LOGEER_PREFIX, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(teams);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@GetMapping(value = "getTeams/{id}", produces = { "application/json" })
	public ResponseEntity<Optional<Team>> getTeamsById(@PathVariable("id") Long id) {
		final String LOGEER_PREFIX = "In getTeamsById:";
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamService.findTeamById(id);
			if (!team.isPresent()) {
				logger.info("{} Could not find team for id={}", LOGEER_PREFIX, id);
			}
		} catch (Exception e) {
			logger.error("{} Error occured for id={}:: message={}, stackTrace={}", LOGEER_PREFIX, id, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@PostMapping(value = "addTeam", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Team> addTeam(@RequestBody TeamWithoutId teamWithoutId) {
		final String LOGEER_PREFIX = "In addTeam:";
		Team team = new Team(teamWithoutId.getTeamName(), teamWithoutId.getGameName(), teamWithoutId.getemailId());
		try {
			team = teamService.saveTeam(team);
		} catch (Exception e) {
			logger.error("{} Error occured:: message={}, stackTrace={}", LOGEER_PREFIX, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@PutMapping(value = "updateTeam/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Optional<Team>> updateTeam(@RequestBody Team team) {
		final String LOGEER_PREFIX = "In updateTeam:";
		Optional<Team> response = Optional.ofNullable(new Team());
		try {
			if (teamService.updateTeam(team)) {
				response = teamService.findTeamById(team.getId());
			} else {
				logger.info("{} Could not update details for id={}", LOGEER_PREFIX, team.getId());
			}
		} catch (Exception e) {
			logger.error("{} Error occured:: message={}, stackTrace={}", LOGEER_PREFIX, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@DeleteMapping(value = "deleteTeam/{id}", produces = { "application/json" })
	public ResponseEntity<Optional<Team>> deleteTeamById(@PathVariable("id") Long id) {
		final String LOGEER_PREFIX = "In deleteTeamById:";
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamService.deleteTeamById(id);
			if (!team.isPresent()) {
				logger.info("{} Could not delete team with id={}", LOGEER_PREFIX, id);
			}
		} catch (Exception e) {
			logger.error("{} Error occured for id:{}: message={}, stackTrace={}", LOGEER_PREFIX, id, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasRole('ROLE_user')")
	@DeleteMapping(value = "keepFirst/{x}", produces = { "application/json" })
	public ResponseEntity<List<Team>> keepFirstX(@PathVariable("x") Long x) {
		final String LOGEER_PREFIX = "In keepFirstX:";
		List<Team> deletedTeams = new ArrayList<>();
		try {
			deletedTeams = teamService.keepFirstX(x);
		} catch (Exception e) {
			logger.error("{} Error occured for x={}: message={}, stackTrace={}", LOGEER_PREFIX, x, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(deletedTeams);
	}

}
