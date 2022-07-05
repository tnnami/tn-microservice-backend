package com.springbootkeycloack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootkeycloack.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
