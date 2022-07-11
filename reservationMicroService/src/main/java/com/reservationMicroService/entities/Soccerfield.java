package com.reservationMicroService.entities;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Soccerfield {
	
	@Id
	private String idSoccerfield;
	
	
	private String name;
	private String adresse;
	private String image;
	private Integer capacity;
	private Double fees;
	private Boolean availibilitySlot;
	

}
