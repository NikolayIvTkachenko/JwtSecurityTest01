package com.rsh.securitytestjwt.Security_Jwt_Test.service;

import java.util.List;

import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;



public interface UserService {
	
	
	User register(User user);
	
	List<User> getAll();
	
	User findByUsername(String username);
	
	User findById(Long id);
	
	void delete(long id);

}
