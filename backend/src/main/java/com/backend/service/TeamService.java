package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Team;
import com.backend.repository.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> findTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public boolean updateTeam(Team team) {
        if (this.findTeamById(team.getId()).isPresent()) {
            this.saveTeam(team);
            return true;
        }
        return false;
    }

    public Optional<Team> deleteTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            teamRepository.deleteById(id);
        }
        return team;
    }

    public Optional<Team> deleteTeam(Team inputTeam) {
        Optional<Team> team = teamRepository.findById(inputTeam.getId());
        if (team.isPresent()) {
            teamRepository.delete(inputTeam);
            return Optional.of(inputTeam);
        }
        return Optional.empty();
    }

    public List<Team> keepFirstX(Long x) {
        List<Team> deletedTeams = new ArrayList<Team>();
        List<Team> allTeams = this.findAllTeams();
        for (int i = 0; i < allTeams.size(); i++) {
            if (i >= x) {
                deletedTeams.add(allTeams.get(i));
                this.deleteTeam(allTeams.get(i));
            }
        }
        return deletedTeams;
    }

}
