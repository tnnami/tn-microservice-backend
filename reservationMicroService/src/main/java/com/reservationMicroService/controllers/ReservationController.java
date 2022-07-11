package com.reservationMicroService.controllers;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reservationMicroService.entities.Reservation;
import com.reservationMicroService.repositories.ReservationRepository;
import com.reservationMicroService.services.ReservationService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ReservationService reservationService;

	@GetMapping(path="/all")
	public ResponseEntity<List<Reservation>> getReservations(){
		return new ResponseEntity<List<Reservation>>(reservationService.getReservations(),HttpStatus.OK);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){

				reservationService.addReservation(reservation);
		
		return new ResponseEntity<Reservation>(reservation,HttpStatus
				.OK);
		
	}
	@PutMapping(path="/upgradeRequest/{reservationId}")
	public ResponseEntity<Reservation> uproveRequest(@PathVariable String reservationId){
		try {

			Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
			Reservation reservation = optionalReservation.get();
			reservation.setState(true);
			reservationRepository.save(reservation);
			return new ResponseEntity<Reservation>(reservation,HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/deleteAll")
	public ResponseEntity<String> deleteAll() {
		reservationRepository.deleteAll();
		return new ResponseEntity<>("Deleted ALl",HttpStatus.OK);
		
	}
	

	@GetMapping(path="/{reservationId}")
	public ResponseEntity<Reservation> getReservationById(@PathVariable String reservationId){
		try {

			Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
			Reservation reservation = optionalReservation.get();
			return new ResponseEntity<Reservation>(reservation,HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

}
