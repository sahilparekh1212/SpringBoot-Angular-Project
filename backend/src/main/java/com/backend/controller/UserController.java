package com.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PreAuthorize("hasAuthority('ROLE_admin')")
	@GetMapping(value = "getUsers", produces = { "application/json" })
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = new ArrayList<>();
		try {
			users = userService.findAllUsers();
		} catch (Exception e) {
			System.out.println("\nError in getUsers -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(users);
	}

	@PostMapping(value = "addUser", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Boolean> addUser(@RequestBody User user) {
		Boolean res = false;
		try {
			res = userService.saveUser(user);
		} catch (Exception e) {
			System.out.println("\nError in addUser() -> e=" + e.toString() + "\n");
		}
		return ResponseEntity.ok(res);
	}

}
