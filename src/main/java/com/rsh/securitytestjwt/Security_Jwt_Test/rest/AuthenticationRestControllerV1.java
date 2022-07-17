package com.rsh.securitytestjwt.Security_Jwt_Test.rest;

import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsh.securitytestjwt.Security_Jwt_Test.dto.AuthenticationRequestDto;
import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;
import com.rsh.securitytestjwt.Security_Jwt_Test.security.JwtTokenProvider;
import com.rsh.securitytestjwt.Security_Jwt_Test.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserService userService;

	@Autowired
	public AuthenticationRestControllerV1(AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider, UserService userService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}
	
	public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
		try {
			String username = requestDto.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
			
			User user = userService.findByUsername(username);
			
			if (user == null) {
				throw new UsernameNotFoundException("User with username: " + username + " not found");
				
			}
			
			String token = jwtTokenProvider.createToken(username, user.getRoles());
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			responseMap.put("username", username);
			responseMap.put("token", token);
			
			return ResponseEntity.ok(responseMap);
		}catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}
	
}
