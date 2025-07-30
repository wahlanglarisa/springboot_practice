package com.example.student.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResults {
	private String courseName;
	private long totalMarks;
	private long marksObtained;
	private double percentage;
}
