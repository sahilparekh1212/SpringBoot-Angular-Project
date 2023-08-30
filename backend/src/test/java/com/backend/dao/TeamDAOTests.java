package com.backend.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.BackendApplication;
import com.backend.model.Team;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class TeamDAOTests {
    @Autowired
    private TeamDAO teamDAO;

    @Test
    public void findAllTeamsTest() {
        List<Team> dbList = teamDAO.findAll();
        Team teamA = new Team("aTeam", "aGame", "a@email.com");
        Team teamB = new Team("bTeam", "bGame", "b@email.com");
        teamA = teamDAO.save(teamA);
        teamB = teamDAO.save(teamB);
        List<Team> dbList2 = teamDAO.findAll();
        assertEquals(dbList2.size() - dbList.size(), 2);
        teamDAO.deleteById(teamA.getId());
        teamDAO.deleteById(teamB.getId());
    }

    @Test
    public void saveTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        team = teamDAO.save(team);
        Assertions.assertThat(team.getId()).isGreaterThan(0L);
        teamDAO.deleteById(team.getId());
    }

    @Test
    public void findByIdTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        Team expected = teamDAO.save(team);
        Optional<Team> actual = teamDAO.findById(expected.getId());
        assertTrue(actual.isPresent());
        teamDAO.deleteById(expected.getId());
    }

    @Test
    public void deleteByIdTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        Team expected = teamDAO.save(team);
        teamDAO.deleteById(expected.getId());
        assertFalse(teamDAO.findById(expected.getId()).isPresent());
    }

    @Test
    public void deleteTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        Team expected = teamDAO.save(team);
        teamDAO.delete(expected);
        assertFalse(teamDAO.findById(expected.getId()).isPresent());
    }
}