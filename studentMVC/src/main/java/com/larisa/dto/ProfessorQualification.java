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
public class ProfessorQualification {
		private UUID id,profID,qualificationType;
		private String stream;
		private Double percentage;
}
