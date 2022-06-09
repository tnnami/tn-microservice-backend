package com.securityservice;

import com.securityservice.entities.AppRole;
import com.securityservice.entities.User;
import com.securityservice.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)

public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args->{
			accountService.addNewRole(new AppRole(null,"USER"));
			accountService.addNewRole(new AppRole(null,"OWNER"));
			accountService.addNewRole(new AppRole(null,"ADMIN"));
			
			accountService.addNewUser(new User(null,"oussema","gharbi","ouss.gharbii@gmail.com","oussema123",new ArrayList<>()));
			accountService.addNewUser(new User(null,"moumen","hafsi","moumen@gmail.com","moumen123",new ArrayList<>()));

			accountService.addRoleToUser("ouss.gharbii@gmail.com", "USER");
			accountService.addRoleToUser("moumen@gmail.com", "ADMIN");

			
		};
	}




}
