package com.backend.controller;

import java.util.ArrayList;
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

@RestController
@RequestMapping(value = "/api/v1/")
public class UserController {

	@Autowired
	private UserService userService;

	Logger logger = LogManager.getLogger(UserController.class);

	@PreAuthorize("hasAuthority('ROLE_admin')")
	@GetMapping(value = "getUsers", produces = { "application/json" })
	public ResponseEntity<List<User>> getUsers() {
		final String LOGEER_PREFIX = "In getUsers:";
		List<User> users = new ArrayList<>();
		try {
			users = userService.findAllUsers();
		} catch (Exception e) {
			logger.error("{} Error occured: message={}, stackTrace={}", LOGEER_PREFIX, e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(users);
	}

	@PostMapping(value = "addUser", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Boolean> addUser(@RequestBody User user) {
		final String LOGEER_PREFIX = "In addUser:";
		Boolean res = false;
		try {
			res = userService.saveUser(user);
		} catch (Exception e) {
			logger.error("{} Error occured for username={} : message={}, stackTrace={}", LOGEER_PREFIX,
					user.getUsername(), e.getMessage(),
					e.getStackTrace());
		}
		return ResponseEntity.ok(res);
	}

}
