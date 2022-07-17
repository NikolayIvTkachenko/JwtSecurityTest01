package com.rsh.securitytestjwt.Security_Jwt_Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rsh.securitytestjwt.Security_Jwt_Test.model.Status;
import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

	private Long idLong;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String status;
	
	public AdminUserDto() {
		super();
	}

	public AdminUserDto(Long idLong, String username, String firstname, String lastname, String email, String status) {
		super();
		this.idLong = idLong;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.status = status;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User toUser() {
		User user = new User();
		user.setIdLong(idLong);
		user.setUsername(username);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setStatus(Status.valueOf(status));
		return user;
	}
	
	public static AdminUserDto fromUser(User user) {
		AdminUserDto adminUserDto = new AdminUserDto();
		adminUserDto.setIdLong(user.getIdLong());
		adminUserDto.setUsername(user.getUsername());
		adminUserDto.setFirstname(user.getFirstName());
		adminUserDto.setLastname(user.getLastName());
		adminUserDto.setEmail(user.getEmail());
		adminUserDto.setStatus(user.getStatus().name());
		return adminUserDto;
	}
	
}
