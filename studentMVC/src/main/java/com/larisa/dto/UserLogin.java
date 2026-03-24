package com.larisa.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements UserDetails {
	private UserRole userRole;
	private String email;
	public UserLogin(UserRole userRole) {
		super();
		this.userRole = userRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		return authorities;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return userRole.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		this.email=userRole.getEmail();
		return userRole.getEmail();
	}

}
