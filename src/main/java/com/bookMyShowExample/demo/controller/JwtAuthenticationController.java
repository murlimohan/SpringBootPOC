package com.bookMyShowExample.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookMyShowExample.demo.model.pojo.JwtRequest;
import com.bookMyShowExample.demo.model.pojo.JwtResponse;
import com.bookMyShowExample.demo.service.JwtUserDetailsService;
import com.bookMyShowExample.demo.util.JwtUtil;

@RestController
public class JwtAuthenticationController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@RequestMapping(value="/authenticate", method= RequestMethod.POST)
	public ResponseEntity<?> createJwtAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
		authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
		final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token= jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		catch(DisabledException e) {
			throw new Exception("USER_DISABLED",e);
		}
		catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENCIALS", e);
		}
	}
	

}
