package com.reservationMicroService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservationMicroService.entities.Reservation;
import com.reservationMicroService.repositories.ReservationRepository;
import com.reservationMicroService.repositories.SoccerfieldRepository;

import java.util.List;

@Service
public class ReservationService {
	
	@Autowired
	private SoccerfieldRepository soccerfieldRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	


	public List<Reservation> getReservations(){
		return reservationRepository.findAll();
	}
	public void addReservation(Reservation reservation) {	
		
		reservationRepository.save(reservation);
		
		
	}
	
	
	

}
