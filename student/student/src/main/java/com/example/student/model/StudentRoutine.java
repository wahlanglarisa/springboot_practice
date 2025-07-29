package com.example.student.model;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRoutine {
	private Long semester;
	private Time time;
	private String courseName;
	private String studentName;
}
