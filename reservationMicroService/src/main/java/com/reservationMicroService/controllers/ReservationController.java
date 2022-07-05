package com.reservationMicroService.controllers;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reservationMicroService.entities.Reservation;
import com.reservationMicroService.services.ReservationService;

import java.util.List;


@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	
	@Autowired
	private ReservationService reservationService;

	@GetMapping(path="/all")
	public ResponseEntity<List<Reservation>> getReservations(){
		return new ResponseEntity<List<Reservation>>(reservationService.getReservations(),HttpStatus.OK);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<HttpStatus> addReservation(){

		//TODO users..
		Reservation reservation = new Reservation (


		);
		reservationService.addReservation(reservation);
		
		return new ResponseEntity<HttpStatus>(HttpStatus
				.OK);
		
	}

}
