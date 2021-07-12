package com.bookMyShowExample.demo.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {
	
	private static final long serialVersionUID = 3340844021981091203L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secretKey)")
	private String secretKey;

	public String extractUserName(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date ectractExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claim = getAllClaimsFromToken(token);
		return claimResolver.apply(claim);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return ectractExpirationDateFromToken(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username=extractUserName(token);
		return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
