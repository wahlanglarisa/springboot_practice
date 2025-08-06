package com.example.student.model;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfListClasses {
	private long id;
	private String courseName;
	private Time time;
	private String day;
}
