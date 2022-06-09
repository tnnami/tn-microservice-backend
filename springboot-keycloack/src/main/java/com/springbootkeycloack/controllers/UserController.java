package com.springbootkeycloack.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootkeycloack.entities.User;
import com.springbootkeycloack.repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	
	@PostMapping("/add-user")
	@RolesAllowed("USER")

	public ResponseEntity<User> saveUser (@RequestBody User user){
		return ResponseEntity.ok(userRepo.save(user));
		
	}
	
	@GetMapping("/users")
	@RolesAllowed("ADMIN")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok(userRepo.findAll());
	}

}
