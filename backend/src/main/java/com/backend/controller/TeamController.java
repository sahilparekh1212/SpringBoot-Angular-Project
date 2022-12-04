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
import com.backend.repository.TeamRepository;
import com.backend.utility.TeamWithoutId;

@RestController
@RequestMapping("/api/v1/")
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("getTeams")
	public List<Team> getTeams() {
		List<Team> res = new ArrayList<Team>();
		try {
			res.addAll(0, teamRepository.findAll());
		} catch (Exception e) {
			System.out.printf("\nError in getTeams -> e=" + e.toString() + "\n");
		}
		return res;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("getTeams/{id}")
	public ResponseEntity<Optional<Team>> getTeams(@PathVariable Long id) {
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamRepository.findById(id);
		} catch (Exception e) {
			System.out.printf("\nError in getTeams -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("addTeam")
	public ResponseEntity<Team> addTeam(@RequestBody TeamWithoutId teamWithoutId) {
		Team team = new Team();
		team.setId(Long.getLong(null, 0));
		team.setTeamName(teamWithoutId.getTeamName());
		team.setGameName(teamWithoutId.getGameName());
		team.setemailId(teamWithoutId.getemailId());
		try {
			team = teamRepository.save(team);
		} catch (Exception e) {
			System.out.printf("\nError in addTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("updateTeam/{id}")
	public ResponseEntity<Optional<Team>> updateTeam(@PathVariable Long id, @RequestBody Team team) {
		Optional<Team> response = Optional.ofNullable(new Team());
		try {
			if (id == team.getId()) {
				response = teamRepository.findById(team.getId());
				if (response != null) {
					teamRepository.save(team);
				} else {
					System.out.printf("\nError in updateTeam() -> Team with id=" + team.getId() + " doesn't exist\n");
				}
			} else {
				System.out.printf("\nError in updateTeam() -> Id mismatch. URL has id=" + id
						+ ". Payload has Team object with id=" + team.getId() + "\n");
			}
		} catch (Exception e) {
			System.out.printf("\nError in updateTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("deleteTeam/{id}")
	public ResponseEntity<Optional<Team>> deleteTeam(@PathVariable Long id) {
		Optional<Team> team = Optional.ofNullable(new Team());
		try {
			team = teamRepository.findById(id);
			if (team != null) {
				teamRepository.deleteById(id);
			} else {
				System.out.printf("\nError in updateTeam() -> Team with id=" + id + " doesn't exist\n");
			}
		} catch (Exception e) {
			System.out.printf("\nError in deleteTeam() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(team);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("keepFirst/{x}")
	public ResponseEntity<List<Team>> keepFirstX(@PathVariable Long x) {
		List<Team> allTeams = new ArrayList<Team>();
		List<Team> deletedTeams = new ArrayList<Team>();
		try {
			allTeams = teamRepository.findAll();
			for (int i = 0; i < allTeams.size(); i++) {
				if (i >= x) {
					deletedTeams.add(allTeams.get(i));
					teamRepository.delete(allTeams.get(i));
				}
			}
		} catch (Exception e) {
			System.out.printf("\nError in keepFirstX() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(deletedTeams);
	}

}
