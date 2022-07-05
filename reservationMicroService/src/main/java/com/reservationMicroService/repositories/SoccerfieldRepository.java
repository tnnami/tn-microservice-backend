package com.reservationMicroService.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reservationMicroService.entities.Soccerfield;

public interface SoccerfieldRepository  extends MongoRepository<Soccerfield, Long>{
	
	

}
