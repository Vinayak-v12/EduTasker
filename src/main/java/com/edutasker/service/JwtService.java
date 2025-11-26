package com.edutasker.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.edutasker.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String SECRET_KEY_BASE_64= "cJA9qVoXjVdLcGuuXc0QDdtaWmV5d8m+EZrclCxpfLU=";
	
	
	public SecretKey getKey() {
		byte[] arr=Base64.getDecoder().decode(SECRET_KEY_BASE_64);
		return Keys.hmacShaKeyFor(arr);
	}
	
	public String getToken(User user) {
		return Jwts.builder()
		.subject(user.getEmail())
		.claim("ROLE",user.getRole())
		.claim("ID",user.getId())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+2*60*60*1000))
		.signWith(getKey())
		.compact();
		
	}
	public String extractEmail(String token) {
	    return Jwts.parser()
	            .verifyWith(getKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
	    String email = extractEmail(token);
	    return email.equals(userDetails.getUsername());
	}

}
