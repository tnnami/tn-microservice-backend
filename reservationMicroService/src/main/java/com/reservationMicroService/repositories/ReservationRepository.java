package com.reservationMicroService.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reservationMicroService.entities.Reservation;

public interface ReservationRepository extends MongoRepository<Reservation, Long> {

}
