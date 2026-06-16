package com.larisa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStudentData {
	private Long phone_no,id;
	private String email,last_name,first_name,user_id,userStatus;
	private byte[] profile_picture;
}
