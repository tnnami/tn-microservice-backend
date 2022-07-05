package com.reservationMicroService.entities;

import java.util.Date;

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
public class Reservation {
	
	@Id
	private Long Id;
	private String reservationIssuerEmail;
	private Soccerfield soccerField;
	private Boolean state;
	private Date startDate;
	private Date endDate;
	private String teamId;

}
