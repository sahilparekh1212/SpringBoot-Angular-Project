package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PreAuthorize("hasAuthority('ROLE_user')")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "getTeams", produces = { "application/json" })
	public ResponseEntity<List<Team>> getTeams() {
		List<Team> teams = new ArrayList<>();
		try {
			teams = teamService.findAllTeams();
		} catch (Exception e) {
			System.out.println("\nError in getTeams -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(teams);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "getTeams/{id}", produces = { "application/json" })
	public ResponseEntity<Optional<Team>> getTeams(@PathVariable("id") Long id) {
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamService.findTeamById(id);
		} catch (Exception e) {
			System.out.println("\nError in getTeams -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "addTeam", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Team> addTeam(@RequestBody TeamWithoutId teamWithoutId) {
		Team team = new Team(teamWithoutId.getTeamName(), teamWithoutId.getGameName(), teamWithoutId.getemailId());
		try {
			team = teamService.saveTeam(team);
		} catch (Exception e) {
			System.out.println("\nError in addTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "updateTeam/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Optional<Team>> updateTeam(@RequestBody Team team) {
		Optional<Team> response = Optional.ofNullable(new Team());
		try {
			if (teamService.updateTeam(team)) {
				response = teamService.findTeamById(team.getId());
			} else {
				System.out.println("\n Could not update details for id=" + team.getId() + "\n");
			}
		} catch (Exception e) {
			System.out.println("\nError in updateTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "deleteTeam/{id}", produces = { "application/json" })
	public ResponseEntity<Optional<Team>> deleteTeam(@PathVariable("id") Long id) {
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamService.deleteTeamById(id);
			if (!team.isPresent()) {
				System.out.println("\nError in updateTeam() -> Team with id=" + id + " doesn't exist\n");
			}
		} catch (Exception e) {
			System.out.println("\nError in deleteTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@PreAuthorize("hasAuthority('ROLE_user')")
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "keepFirst/{x}", produces = { "application/json" })
	public ResponseEntity<List<Team>> keepFirstX(@PathVariable("x") Long x) {
		List<Team> deletedTeams = new ArrayList<>();
		try {
			deletedTeams = teamService.keepFirstX(x);
		} catch (Exception e) {
			System.out.println("\nError in keepFirstX() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(deletedTeams);
	}

}
