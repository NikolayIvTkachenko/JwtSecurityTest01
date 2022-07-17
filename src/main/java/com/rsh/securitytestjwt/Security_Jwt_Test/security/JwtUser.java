package com.rsh.securitytestjwt.Security_Jwt_Test.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.FinalArrayList;

public class JwtUser implements UserDetails {

	private final Long idLong;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final String password;
	private final String email;
	private final boolean enable;
	private final Date lastPasswordResetDate;
	private final Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtUser(Long idLong, String username, String firstName, String lastName, String password, String email,
			boolean enable, Date lastPasswordResetDate, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.idLong = idLong;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.enable = enable;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
	}
	
	@JsonIgnore
	public Long getId() {return idLong;}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

	
	
	
}
