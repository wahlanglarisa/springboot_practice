package com.larisa.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
	private String firstName,lastName,middleName,userID,fullName;
	private UUID id;
	private Long phoneNo;
	
	public Professor(String firstName, String lastName, String middleName, String userID, Long phoneNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.userID = userID;
		this.phoneNo = phoneNo;
		this.fullName=firstName+" "+lastName;
	}
	
}
