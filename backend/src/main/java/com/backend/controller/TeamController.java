package com.backend.controller;

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

import com.backend.exceptions.SomeCustomException;
import com.backend.model.Team;
import com.backend.service.TeamService;
import com.backend.util.Constants;
import com.backend.utility.TeamWithoutId;

@RestController
@RequestMapping(value = Constants.API_BASE_URL)
public class TeamController {

	@Autowired
	private TeamService teamService;

	Logger logger = LogManager.getLogger(TeamController.class);

	@PreAuthorize("hasAuthority('ROLE_user')")
	@GetMapping(value = "getTeams", produces = { "application/json" })
	public ResponseEntity<List<Team>> getTeams() {
		return ResponseEntity.ok(teamService.findAllTeams());
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@GetMapping(value = "getTeams/{id}", produces = { "application/json" })
	public ResponseEntity<Optional<Team>> getTeamsById(@PathVariable("id") Long id) {
		final String LOGEER_PREFIX = "In getTeamsById:";
		Optional<Team> team = teamService.findTeamById(id);
		if (!team.isPresent()) {
			logger.info("{} Could not find team for id={}", LOGEER_PREFIX, id);
			throw new SomeCustomException("No Team Found with id=" + id);
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@PostMapping(value = "addTeam", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Team> addTeam(@RequestBody TeamWithoutId teamWithoutId) {
		Team team = new Team(teamWithoutId.getTeamName(), teamWithoutId.getGameName(), teamWithoutId.getemailId());
		team = teamService.saveTeam(team);
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@PutMapping(value = "updateTeam/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Optional<Team>> updateTeam(@RequestBody Team team, @PathVariable("id") Long id) {
		final String LOGEER_PREFIX = "In updateTeam:";
		if (teamService.updateTeam(team)) {
			return ResponseEntity.ok(teamService.findTeamById(team.getId()));
		} else {
			logger.info("{} Could not update details for id={}", LOGEER_PREFIX, team.getId());
			throw new SomeCustomException("Could Not Update team with id=" + id);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@DeleteMapping(value = "deleteTeam/{id}", produces = { "application/json" })
	public ResponseEntity<Optional<Team>> deleteTeamById(@PathVariable("id") Long id) {
		final String LOGEER_PREFIX = "In deleteTeamById:";
		Optional<Team> team = teamService.deleteTeamById(id);
		if (!team.isPresent()) {
			logger.info("{} Could not delete team with id={}", LOGEER_PREFIX, id);
			throw new SomeCustomException("Could not delete team with id=" + id);
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasRole('ROLE_user')")
	@DeleteMapping(value = "keepFirst/{x}", produces = { "application/json" })
	public ResponseEntity<List<Team>> keepFirstX(@PathVariable("x") Long x) {
		return ResponseEntity.ok(teamService.keepFirstX(x));
	}

}
