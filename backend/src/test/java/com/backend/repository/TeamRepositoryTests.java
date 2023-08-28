package com.backend.repository;

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
public class TeamRepositoryTests {
    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void findAllTeamsTest() {
        List<Team> dbList = teamRepository.findAll();
        Team teamA = new Team("aTeam", "aGame", "a@email.com");
        Team teamB = new Team("bTeam", "bGame", "b@email.com");
        teamA = teamRepository.save(teamA);
        teamB = teamRepository.save(teamB);
        List<Team> dbList2 = teamRepository.findAll();
        assertEquals(dbList2.size() - dbList.size(), 2);
        teamRepository.deleteById(teamA.getId());
        teamRepository.deleteById(teamB.getId());
    }

    @Test
    public void saveTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        team = teamRepository.save(team);
        Assertions.assertThat(team.getId()).isGreaterThan(0L);
        teamRepository.deleteById(team.getId());
    }

    @Test
    public void findByIdTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        Team expected = teamRepository.save(team);
        Optional<Team> actual = teamRepository.findById(expected.getId());
        assertTrue(actual.isPresent());
        teamRepository.deleteById(expected.getId());
    }

    @Test
    public void deleteByIdTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        Team expected = teamRepository.save(team);
        teamRepository.deleteById(expected.getId());
        assertFalse(teamRepository.findById(expected.getId()).isPresent());
    }

    @Test
    public void deleteTeamTest() {
        Team team = new Team("aTeam", "aGame", "a@email.com");
        Team expected = teamRepository.save(team);
        teamRepository.delete(expected);
        assertFalse(teamRepository.findById(expected.getId()).isPresent());
    }
}