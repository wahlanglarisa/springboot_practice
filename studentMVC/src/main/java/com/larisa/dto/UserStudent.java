package com.larisa.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserStudent {
	public UserStudent(Long phone_no, Long st_id, String password, String email, String district_code,
			String state_code, String country_code, String last_name, String first_name, String user_id,
			String address,byte[] profile_picture) {
		super();
		this.phone_no = phone_no;
		this.st_id = st_id;
		this.password = password;
		this.email = email;
		this.district_code = district_code;
		this.state_code = state_code;
		this.country_code = country_code;
		this.last_name = last_name;
		this.first_name = first_name;
		this.user_id = user_id;
		this.address = address;
		this.profile_picture =profile_picture;
	}
	private Long phone_no,st_id;
	private String password, email, district_code, state_code, country_code, last_name, first_name,user_id,address;
	private MultipartFile proFile;
	private byte[] profile_picture;

}
