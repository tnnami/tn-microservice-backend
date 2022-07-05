package com.Teammicroservice.controllers;

import java.util.Collection;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teammicroservice.entities.Team;
import com.Teammicroservice.repositories.TeamRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/team")
public class TeamController {
	
	
	@Autowired
	TeamRepository teamRepo ;
	
	
	@PostMapping(path="/add")
	public ResponseEntity<Team> createTeam(@RequestBody Team team){
		try {
		
			Team myTeam = teamRepo.save(new Team(team.getTeamAdmin(),team.getTeamMembers()));
			return new ResponseEntity<>(myTeam,HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Team> getTeamById(@PathVariable String id){
		try {
			Optional<Team> optionalTeam = teamRepo.findById(id);
			Team team = optionalTeam.get();
			return new ResponseEntity<Team>(team,HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	@PutMapping(path="/update/{id}")
	public ResponseEntity<Team> updateTeamById(@PathVariable String id, @RequestBody Team team){
		try {
			Optional<Team> optionalTeam = teamRepo.findById(id);
			Team myTeam = optionalTeam.get();
			myTeam.setTeamMembers(team.getTeamMembers());
			teamRepo.save(myTeam);
			return new ResponseEntity<Team>(myTeam,HttpStatus.OK);

					
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	

}
