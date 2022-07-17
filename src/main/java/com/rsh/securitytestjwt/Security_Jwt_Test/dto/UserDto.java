package com.rsh.securitytestjwt.Security_Jwt_Test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rsh.securitytestjwt.Security_Jwt_Test.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

	private Long idLong;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	
	public UserDto() {
		super();
	}

	public UserDto(Long idLong, String username, String firstname, String lastname, String email) {
		super();
		this.idLong = idLong;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
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
	
	
	public User toUser() {
		User user = new User();
		user.setIdLong(idLong);
		user.setUsername(username);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		return user;
	}
	
	public static UserDto fromUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setIdLong(user.getIdLong());
		userDto.setUsername(user.getUsername());
		userDto.setFirstname(user.getFirstName());
		userDto.setLastname(user.getLastName());
		userDto.setEmail(user.getEmail());
		
		return userDto;
	}
	
}
