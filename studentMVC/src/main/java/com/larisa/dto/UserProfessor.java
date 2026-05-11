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
@AllArgsConstructor
public class UserProfessor {
	private String firstName,lastName,middleName,userID,email,password;
	private UUID profID;
	private List<ProfessorQualification> professorQualifications=new ArrayList<ProfessorQualification>();
	private Long phoneNo;
}
