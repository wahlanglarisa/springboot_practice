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
	private String email,last_name,first_name,user_id;
	private byte[] profile_picture;
	public Student(Long phone_no,  String email,
			String last_name, String first_name,String user_id) {
		super();
		this.phone_no = phone_no;
		this.email = email;
		
		this.last_name = last_name;
		this.first_name = first_name;
		this.user_id=user_id;
	}
	
}
