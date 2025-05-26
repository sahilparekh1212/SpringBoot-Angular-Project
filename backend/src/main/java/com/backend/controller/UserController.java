package com.backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.service.UserService;
import com.backend.util.Constants;

@RestController
@RequestMapping(value = Constants.API_BASE_URL)
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	Logger logger = LogManager.getLogger(UserController.class);

	@PreAuthorize("hasAuthority(Constants.AUTHORITY_ADMIN)")
	@GetMapping(value = "getUsers", produces = { "application/json" })
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@PostMapping(value = "addUser", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Boolean> addUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.saveUser(user));
	}

}
