package com.larisa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private String userid,password,email,roleid;

	public User(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}
	
}
