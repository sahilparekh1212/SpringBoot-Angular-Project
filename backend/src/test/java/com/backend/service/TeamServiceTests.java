package com.backend.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.BackendApplication;
import com.backend.dao.TeamDAO;
import com.backend.model.Team;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class TeamServiceTests {

    @InjectMocks
    TeamService teamService;

    @Mock
    private TeamDAO teamDAO;

    @Test
    public void findAllTeamsTest() {
        List<Team> daoRes = new ArrayList<>();
        daoRes.add(new Team("aTeam", "aGame", "a@a.com"));
        when(teamDAO.findAll()).thenReturn(daoRes);
        assertEquals(teamService.findAllTeams(), daoRes);
    }

    @Test
    public void findTeamByIdTest() {
        Optional<Team> daoRes = Optional.of(new Team("aTeam", "aGame", "a@a.com"));
        when(teamDAO.findById(Long.valueOf(1L))).thenReturn(daoRes);
        assertEquals(teamService.findTeamById(1L), daoRes);
    }

    @Test
    public void saveTeamTest() {
        Team daoRes = new Team("aTeam", "aGame", "a@a.com");
        when(teamDAO.save(daoRes)).thenReturn(daoRes);
        assertEquals(teamService.saveTeam(daoRes), daoRes);
    }

    @Test
    public void updateTeamTest() {
        Team input = new Team("aTeam", "aGame", "a@a.com");
        input.setId(1L);
        when(teamDAO.save(input)).thenReturn(input);
        when(teamService.findTeamById(1L)).thenReturn(Optional.of(input));
        when(teamDAO.save(null)).thenReturn(null);
        when(teamService.findTeamById(null)).thenReturn(Optional.empty());
        assertEquals(teamService.updateTeam(input), true);
        assertEquals(teamService.updateTeam(null), false);
    }

    @Test
    public void deleteTeamByIdTest() {
        Team team = new Team("aTeam", "aGame", "a@a.com");
        team.setId(1L);
        when(teamDAO.findById(1L)).thenReturn(Optional.of(team));
        when(teamDAO.findById(null)).thenReturn(Optional.empty());
        assertEquals(teamService.deleteTeamById(1l), Optional.of(team));
        assertEquals(teamService.deleteTeamById(null), Optional.empty());
    }

    @Test
    public void deleteTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@a.com");
        team.setId(1L);
        when(teamDAO.findById(1L)).thenReturn(Optional.of(team));
        when(teamDAO.findById(null)).thenReturn(Optional.empty());
        assertEquals(teamService.deleteTeam(team), Optional.of(team));
        assertEquals(teamService.deleteTeam(null), Optional.empty());
    }

    @Test
    public void keepFirstXTest() {
        Team team = new Team("aTeam", "aGame", "a@a.com");
        List<Team> teams = new ArrayList<>();
        teams.add(team);
        when(teamService.findAllTeams()).thenReturn(teams);
        when(teamDAO.findById(null)).thenReturn(Optional.empty());
        assertEquals(teamService.keepFirstX(0L), teams);
        assertEquals(teamService.keepFirstX(1L), new ArrayList<>());
    }
}
