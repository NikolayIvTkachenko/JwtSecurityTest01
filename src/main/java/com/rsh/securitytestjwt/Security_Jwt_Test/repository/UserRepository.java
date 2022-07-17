package com.rsh.securitytestjwt.Security_Jwt_Test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String name);

}
