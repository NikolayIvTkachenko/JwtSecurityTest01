package com.rsh.securitytestjwt.Security_Jwt_Test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;
import com.rsh.securitytestjwt.Security_Jwt_Test.service.UserService;

import liquibase.pro.packaged.iF;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	private final UserService userService;
	
	@Autowired
	public JwtUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//log.info("IN  delete - user with id: {} successfully delete");
		User user = userService.findByUsername(username);
		
		if(user == null){
			throw new UsernameNotFoundException("User with username: " + username + " not found.");
		}
		
		JwtUser jwtUser = JwtUserFactory.create(user);
		
		return null;
	}

	
	
}
