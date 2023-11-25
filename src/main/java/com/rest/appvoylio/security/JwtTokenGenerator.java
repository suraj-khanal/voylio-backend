package com.rest.appvoylio.security;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenGenerator.class);
	
	public String generateToken(Authentication authentication) {
		
		String username = authentication.getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		logger.info("Roles for user {}: {}", username, authorities);
		
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
		
		
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
		
		claims.put("Author", "SurajKhanal");
//	    claims.put("Quote", "The only way to do great work is to love what you do");
		
		String token = Jwts.builder()
				.addClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
				.compact();
		
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
		Claims  claims = Jwts.parser()
				.setSigningKey(SecurityConstants.JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();				
	}
	
	  public String getRoleFromJwtToken(String token) {
		    return (String)Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token).getBody().get("auth");
	 }
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
			return true;
		} 
//		catch (Exception ex) {
//			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
//		}
		catch (SignatureException e) {
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



