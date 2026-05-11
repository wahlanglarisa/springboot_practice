package com.larisa.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserStudent {
	public UserStudent(Long phone_no, Long st_id, String password, String email,  String last_name, String first_name, String user_id, 
			byte[] profile_picture, UUID creationStatusID,
			String permaddressline1,String permaddressline2,String permaddressline3,
			String preaddressline1,String preaddressline2,String preaddressline3,long permaddrpin, long preaddrpin) {
		super();
		this.phone_no = phone_no;
		this.st_id = st_id;
		this.password = password;
		this.email = email;
		this.last_name = last_name;
		this.first_name = first_name;
		this.user_id = user_id;
		this.profile_picture = profile_picture;
		this.creationStatusID = creationStatusID;
		this.permaddressline1=permaddressline1;
		this.permaddressline2=permaddressline2;
		this.permaddressline3=permaddressline3;
		this.preaddressline1=preaddressline1;
		this.preaddressline2=preaddressline2;
		this.preaddressline3=preaddressline3;
		this.permaddrpin=permaddrpin;
		this.preaddrpin=preaddrpin;
	}

	private Long phone_no, st_id;
	private String password, email, last_name, first_name, user_id,
			permaddressline1, permaddressline2, permaddressline3,permCountryCode,permStateCode,permDistrictCode, preaddressline1, preaddressline2, preaddressline3,preCountryCode,preStateCode,preDistrictCode,user_creation_status;
	private long permaddrpin, preaddrpin;
	private MultipartFile proFile;
	private byte[] profile_picture;
	private UUID creationStatusID;
	private List<StudentQualification> studentQualifications=new ArrayList<StudentQualification>();
	private List<UUID> ids=new ArrayList<UUID>();
}
