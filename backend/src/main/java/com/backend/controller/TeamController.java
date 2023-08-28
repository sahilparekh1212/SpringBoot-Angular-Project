package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("getTeams")
	public List<Team> getTeams() {
		List<Team> res = new ArrayList<Team>();
		try {
			res = teamService.findAllTeams();
		} catch (Exception e) {
			System.out.println("\nError in getTeams -> e=" + e.toString() + "\n");
		}
		return res;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("getTeams/{id}")
	public ResponseEntity<Optional<Team>> getTeams(@PathVariable Long id) {
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamService.findTeamById(id);
		} catch (Exception e) {
			System.out.println("\nError in getTeams -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("addTeam")
	public ResponseEntity<Team> addTeam(@RequestBody TeamWithoutId teamWithoutId) {
		Team team = new Team(teamWithoutId.getTeamName(), teamWithoutId.getGameName(), teamWithoutId.getemailId());
		try {
			team = teamService.saveTeam(team);
		} catch (Exception e) {
			System.out.println("\nError in addTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("updateTeam/{id}")
	public ResponseEntity<Optional<Team>> updateTeam(@PathVariable Long id, @RequestBody Team team) {
		Optional<Team> response = Optional.ofNullable(new Team());
		try {
			if (teamService.updateTeam(team)) {
				response = teamService.findTeamById(team.getId());
			} else {
				System.out.println("\nError in updateTeam() -> Id mismatch. URL has id=" + id
						+ ". Payload has Team object with id=" + team.getId() + "\n");
			}
		} catch (Exception e) {
			System.out.println("\nError in updateTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("deleteTeam/{id}")
	public ResponseEntity<Optional<Team>> deleteTeam(@PathVariable Long id) {
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

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("keepFirst/{x}")
	public ResponseEntity<List<Team>> keepFirstX(@PathVariable Long x) {
		List<Team> deletedTeams = new ArrayList<Team>();
		try {
			deletedTeams = teamService.keepFirstX(x);
		} catch (Exception e) {
			System.out.println("\nError in keepFirstX() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(deletedTeams);
	}

}
