package com.cognixia.jump.config;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	
	// Authentication - who are you?
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return userDetailsService;
	}
	
	// Authentication - what do you want?
		@Bean
		protected SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
			
			http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/authenticate").permitAll() // allow anyone to make a request to create a JWT
				.antMatchers(HttpMethod.POST, "/api/user").permitAll() // let anyone create a user
				.anyRequest().authenticated() // allow anyone in if they are a user
				.and()
				.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ); // tell security NOT TO CREATE any sessions
			
		
			// typically the first filter that is checked is the UsernamePasswordAuthenticationFilter, however if this filter is checked
			// first, there is no username or password to check, so security will block request
			// so we make sure the jwt filter is checked first, so we can load the jwt in and load in the user before any other filter
			// is checked
			
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class );
			
			
			return http.build();
		}
}
