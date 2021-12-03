package com.pathsmentorship.pathsbackend.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pathsmentorship.pathsbackend.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${pathsmentorship.app.jwtSecret}")
	private String jwtSecret;

	@Value("${pathsmentorship.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

//		System.out.println("In JwtUtils: generate token");
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		
//		System.out.println("userPrincipal: " + userPrincipal);
//		System.out.println("userPrincipal.getusername: " + userPrincipal.getUsername());
//		System.out.println("userPrincipal.getpassword: " + userPrincipal.getPassword());
		
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + Long.valueOf(jwtExpirationMs)))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
//		System.out.println("In JwtUtils getUserNameFromJwtToken");
//		System.out.println("token: " + token);
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		
//		System.out.println("In validateJwtToken");
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}