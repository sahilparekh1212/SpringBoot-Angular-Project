package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Team;
import com.backend.repository.TeamRepository;

@RestController
@RequestMapping("/api/v1/")
public class TeamController {
	
		@Autowired
		private TeamRepository teamRepository;
		
		//Get All Teams REST API
		@GetMapping("teams")
		public List<Team> getAllTeams(){
			return teamRepository.findAll();
		}
}
