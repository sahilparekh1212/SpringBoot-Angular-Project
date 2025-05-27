package com.backend.controller;

import com.backend.dto.TeamDTO;
import com.backend.model.Team;
import com.backend.service.TeamService;
import com.backend.util.Constants;
import com.backend.utility.TeamWithoutId;
import com.backend.utility.Util;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class TeamControllerTest {

        @Autowired
        private WebApplicationContext webApplicationContext;

        @MockBean
        private TeamService teamService;

        private MockMvc mockMvc;

        @BeforeEach
        void setUp() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        Team getValidTeam() {
            return new Team(99L, "someTeamName", "someGameName", "a@b.com");
        }

        TeamWithoutId getValidTeamWithoutId() {
            return new TeamWithoutId("someTeamName", "someGameName", "a@b.com");
        }

        TeamDTO getValidTeamDTO() {
            return new TeamDTO(99L, "someTeamName", "someGameName", "a@b.com");
        }

        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testAddTeam_valid() throws Exception {
            TeamWithoutId teamWithoutId = getValidTeamWithoutId();
            String endPoint = Constants.API_BASE_URL + "/addTeam";
            when(teamService.saveTeam(Mockito.any(Team.class))).thenReturn(getValidTeam());
            mockMvc.perform(
                post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.getJSON(teamWithoutId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamName").value(teamWithoutId.getTeamName()))
                .andExpect(jsonPath("$.gameName").value(teamWithoutId.getGameName()))
                .andExpect(jsonPath("$.emailId").value(teamWithoutId.getEmailId()));
        }

        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testAddTeam_invalid_byEmail() throws Exception {
            TeamWithoutId teamWithoutId = getValidTeamWithoutId();
            teamWithoutId.setEmailId(null);
            String endPoint = Constants.API_BASE_URL + "/addTeam";
            mockMvc.perform(
                    post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamWithoutId)))
                    .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testAddTeam_invalid_byGameName() throws Exception {
            TeamWithoutId teamWithoutId = getValidTeamWithoutId();
            teamWithoutId.setGameName(null);
            String endPoint = Constants.API_BASE_URL + "/addTeam";
            mockMvc.perform(
                    post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamWithoutId)))
                    .andExpect(status().isUnprocessableEntity());
        }


        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testAddTeam_invalid_byTeamName() throws Exception {
            TeamWithoutId teamWithoutId = getValidTeamWithoutId();
            teamWithoutId.setTeamName(null);
            String endPoint = Constants.API_BASE_URL + "/addTeam";
            mockMvc.perform(
                    post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamWithoutId)))
                    .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testUpdateTeam_valid() throws Exception {
            TeamDTO teamDTO = getValidTeamDTO();
            String endPoint = Constants.API_BASE_URL + "/updateTeam/" + teamDTO.getId();
            when(teamService.updateTeam(Mockito.any(Team.class))).thenReturn(true);
            when(teamService.findTeamById(Mockito.anyLong())).thenReturn(Optional.of(getValidTeam()));
            mockMvc.perform(
                    put(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.teamName").value(teamDTO.getTeamName()))
                    .andExpect(jsonPath("$.gameName").value(teamDTO.getGameName()))
                    .andExpect(jsonPath("$.emailId").value(teamDTO.getEmailId()))
                    .andExpect(jsonPath("$.id").value(teamDTO.getId()));
        }

        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testUpdateTeam_invalid_byEmail() throws Exception {
            TeamDTO teamDTO = getValidTeamDTO();
            teamDTO.setEmailId(null);
            String endPoint = Constants.API_BASE_URL + "/updateTeam/" + teamDTO.getId();
            mockMvc.perform(
                    put(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamDTO)))
                    .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testUpdateTeam_invalid_byGameName() throws Exception {
            TeamDTO teamDTO = getValidTeamDTO();
            teamDTO.setGameName(null);
            String endPoint = Constants.API_BASE_URL + "/updateTeam/" + teamDTO.getId();
            mockMvc.perform(
                    put(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamDTO)))
                    .andExpect(status().isUnprocessableEntity());
        }


        @Test
        @WithMockUser(authorities = {"ROLE_user","ROLE_admin"})
        void testUpdateTeam_invalid_byTeamName() throws Exception {
            TeamDTO teamDTO = getValidTeamDTO();
            teamDTO.setTeamName(null);
            String endPoint = Constants.API_BASE_URL + "/updateTeam/" + teamDTO.getId();
            mockMvc.perform(
                    put(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Util.getJSON(teamDTO)))
                    .andExpect(status().isUnprocessableEntity());
        }

}
