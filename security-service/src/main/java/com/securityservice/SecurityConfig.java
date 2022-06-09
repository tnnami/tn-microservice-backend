package com.securityservice;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.securityservice.entities.User;
import com.securityservice.filters.JwtAuthenticationFilter;
import com.securityservice.filters.JwtAuthorizationFilter;
import com.securityservice.services.AccountService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AccountService accountService;
	//users that they have permission to access
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user = accountService.loadUserByEmail(username);
				Collection<GrantedAuthority> authorities = new ArrayList<>();
				user.getRoles().forEach(r->{
					authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
				});
				return  new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
			}
		});
	}
	
	//permissions
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//for test of h2 (frame options and CRSF security must be disabled to test with h2)
	    http.csrf().disable(); // because it uses the sessions 
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
	    http.authorizeRequests().antMatchers("/h2-console/**","/refreshToken/**","/login/**","/signon/**").permitAll();
		http.headers().frameOptions().disable();
		//http.formLogin();
		//http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("ADMIN");
		//http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("ADMIN");
		//http.authorizeRequests().antMatchers(HttpMethod.PUT,"/users/**").hasAuthority("ADMIN");
		//http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/users/**").hasAuthority("ADMIN");
		//http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/users/**").hasAuthority("ADMIN");




		http.authorizeRequests().anyRequest().authenticated();
		
		http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

}
