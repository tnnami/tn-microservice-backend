package com.Teammicroservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Teammicroservice.entities.Team;

public interface TeamRepository extends MongoRepository<Team, String>{

}
