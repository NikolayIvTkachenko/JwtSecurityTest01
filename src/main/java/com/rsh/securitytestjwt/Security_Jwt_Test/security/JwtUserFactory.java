package com.rsh.securitytestjwt.Security_Jwt_Test.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.rsh.securitytestjwt.Security_Jwt_Test.model.Role;
import com.rsh.securitytestjwt.Security_Jwt_Test.model.Status;
import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;

public final class JwtUserFactory {

	public JwtUserFactory () {}
	
	public static JwtUser create(User user) {
		return new JwtUser(
				user.getIdLong(), 
				user.getUsername(), 
				user.getFirstName(),
				user.getLastName(), 
				user.getPassword(), 
				user.getEmail(), 
				user.getStatus().equals(Status.ACTIVE),
				user.getUpdated(),
				mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))); 
	}
	
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
		return roles.stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
