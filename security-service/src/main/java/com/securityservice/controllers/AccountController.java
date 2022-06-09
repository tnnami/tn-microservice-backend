package com.securityservice.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.securityservice.entities.AppRole;
import com.securityservice.entities.User;
import com.securityservice.repositories.AppRoleRepository;
import com.securityservice.services.AccountServiceImpl;

import lombok.Data;

@RestController
public class AccountController {
	
	@Autowired
	AccountServiceImpl accountService;
	@Autowired
	AppRoleRepository roleRepo;
	
	@GetMapping(path="/users")
	public List<User> getAllUsers(){
		
		return accountService.loadAllUsers();
		
	}
	@GetMapping(path="/roles")
	@PostAuthorize("hasAuthority('USER')")
	public List<AppRole> getRoles() {
		return roleRepo.findAll();
		
	}
	@PostMapping(path="/signon")
	public User saveUser(@RequestBody User user) {
		return accountService.addNewUser(user);
		
	}
	@PostMapping(path="/role")
	public AppRole saveRole(@RequestBody AppRole role) {
		return accountService.addNewRole(role);
	}
	@PostMapping(path="/addRoleToUser")
	public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
		 accountService.addRoleToUser(roleUserForm.getEmail(),roleUserForm.getRoleName());
	}
	
	@GetMapping(path="/profile")
	public User profile (Principal principal) {
		return accountService.loadUserByeUserName(principal.getName());
	}
	
	@GetMapping(path="/refreshToken")
	public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String authToken= request.getHeader("Authorization");
		if(authToken !=null && authToken.startsWith("Bearer ") ) {
			try {
				String jwt = authToken.substring(7);
				Algorithm algorithm = Algorithm.HMAC256("my_SecretKey1234");
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decoded = jwtVerifier.verify(jwt);
				String username= decoded.getSubject();
				System.out.println(username);
				User user = accountService.loadUserByeUserName(username);
				System.out.println(user);
				String accessToken = JWT.create()
						.withSubject(user.getUserName())
						.withExpiresAt(new Date(System.currentTimeMillis()+1*60*10000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
						.sign(algorithm);
				
				Map<String, String> idToken = new HashMap<>();
				idToken.put("access-token", accessToken);
				idToken.put("refresh-token", jwt);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), idToken);
				
			} catch (Exception e) {
				
				throw e;
				
			}		
			
		}else {
			throw new RuntimeException("Refresh Token required!!!");
		}
			
			
		
		}
		
	}



@Data
class RoleUserForm{
	private String email;
	private String roleName;
}
