package com.reservationMicroService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reservationMicroService.entities.Reservation;
import com.reservationMicroService.services.ReservationService;


@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	
	@Autowired
	private ReservationService reservationService;
	
	
	@PostMapping(path = "/add")
	public ResponseEntity<HttpStatus> addReservation(){
		
		Reservation reservation = new Reservation ();
		reservationService.addReservation(reservation);
		
		return new ResponseEntity<HttpStatus>(HttpStatus
				.OK);
		
	}

}
