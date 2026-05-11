package com.larisa.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentQualDetails {
	private String qualification,stream;
	private Double percentage;
	private UUID qType,stQid;
}
