package com.rsh.securitytestjwt.Security_Jwt_Test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsh.securitytestjwt.Security_Jwt_Test.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String nameString);
	
}
