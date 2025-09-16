package com.example.student.model.wrapper;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.student.model.Professor;

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
	@DateTimeFormat(pattern = "HH:mm") // 24-hour format
    private LocalTime time;
	private String day;
	private long courseID;
	private Professor professor;
}
