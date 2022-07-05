package com.Teammicroservice.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Team {
	
	@Id
	private String teamId;
	private String teamAdmin;
	public Team(String teamAdmin, List<String> teamMembers) {
		super();
		this.teamAdmin = teamAdmin;
		this.teamMembers = teamMembers;
	}
	private List<String> teamMembers;
	

}
