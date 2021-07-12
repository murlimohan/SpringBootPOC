package com.bookMyShowExample.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookMyShowExample.demo.service.JwtUserDetailsService;
import com.bookMyShowExample.demo.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader=request.getHeader("Authorization");
		String userName=null;
		String jwt=null;
		
		if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
			jwt= authorizationHeader.substring(7);
			try {
				userName=jwtUtil.extractUserName(jwt);
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			}
			catch(ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		}
		else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=jwtUserDetailsService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);	
		
	}

}
