package com.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.backend.BackendApplication;
import com.backend.model.Team;
import com.backend.service.TeamService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class TeamControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamService teamService;

    @Test
    public void getTeamsTest() {

        Mockito.when(teamService.findAllTeams()).thenReturn(null);
        String URI = "/api/v1/getTeams";
        // RequestBuilder rb = MockMvcRequestBuilders.post(URI).accept(Media)
    }
}