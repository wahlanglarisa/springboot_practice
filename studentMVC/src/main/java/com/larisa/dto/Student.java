package com.larisa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class Student {
	private Long phone_no,id;
	private String email,district_code,state_code,country_code,last_name,first_name,user_id,address;
	private byte[] profile_picture;
	public Student(Long phone_no,  String email, String district_code, String state_code, String country_code,
			String last_name, String first_name,String user_id,String address) {
		super();
		this.phone_no = phone_no;
		this.email = email;
		this.district_code = district_code;
		this.state_code = state_code;
		this.country_code = country_code;
		this.last_name = last_name;
		this.first_name = first_name;
		this.user_id=user_id;
		this.address=address;
	}
	
}
