package com.rsh.securitytestjwt.Security_Jwt_Test.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rsh.securitytestjwt.Security_Jwt_Test.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import liquibase.pro.packaged.iF;


@Component
public class JwtTokenProvider {
	
	@Value("${jwt.token.secret}")
	private String secret;
	
	@Value("${jwt.token.expired}")
	private long validityInMilliseconds;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@PostConstruct
	protected void init() {
		secret = java.util.Base64.getEncoder().encodeToString(secret.getBytes());
	}

	public String createToken(String username, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", getRoleNames(roles));
		
		Date nowDate = new Date();
		Date validity = new Date(nowDate.getTime() + validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(nowDate)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerTokenString = req.getHeader("Authorization");
		if(bearerTokenString != null && bearerTokenString.startsWith("Bearer ")){
			return bearerTokenString.substring(7, bearerTokenString.length());
		}
		
		
		return null;
	}
	
	
	public boolean validateToken(String token) throws Exception{
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			if(claimsJws.getBody().getExpiration().before(new Date())){
				return false;
			}
			return true;
			
		}catch(JwtException | IllegalArgumentException ex) {
			throw new JwtAuthenticationException("JWT token is expired or invalid");
		}
	}
	
	private List<String> getRoleNames(List<Role> userRoles){
		List<String> resultList = new ArrayList<>();
		
		userRoles.forEach(Role -> {resultList.add(
				Role.getName());
		});
		
		return resultList;
	}
	
}
