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
public class StudentQualification {
    private UUID id,qualificationType;
    private String qualificationStream;
    private Double percentage;
    private Long stID;

}
