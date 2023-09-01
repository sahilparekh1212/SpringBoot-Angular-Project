package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dao.TeamDAO;
import com.backend.model.Team;

@Service
public class TeamService {

    @Autowired
    private TeamDAO teamDAO;

    public List<Team> findAllTeams() {
        return teamDAO.findAll();
    }

    public Optional<Team> findTeamById(Long id) {
        return teamDAO.findById(id);
    }

    public Team saveTeam(Team team) {
        return teamDAO.save(team);
    }

    public boolean updateTeam(Team team) {
        if (team != null && this.findTeamById(team.getId()).isPresent()) {
            this.saveTeam(team);
            return true;
        }
        return false;
    }

    public Optional<Team> deleteTeamById(Long id) {
        Optional<Team> team = teamDAO.findById(id);
        if (team.isPresent()) {
            teamDAO.deleteById(id);
        }
        return team;
    }

    public Optional<Team> deleteTeam(Team inputTeam) {
        if (inputTeam != null) {
            Optional<Team> team = teamDAO.findById(inputTeam.getId());
            if (team.isPresent()) {
                teamDAO.delete(inputTeam);
                return Optional.of(inputTeam);
            }
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
