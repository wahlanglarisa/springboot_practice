package com.larisa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	private Boolean isElective;
	private Long semester,id,credit;
	private String courseName;
	
	
	
}
