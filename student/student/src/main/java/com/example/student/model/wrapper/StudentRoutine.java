package com.example.student.model.wrapper;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "HH:mm") // 24-hour format
    private LocalTime starttime;
	@DateTimeFormat(pattern = "HH:mm") // 24-hour format
    private LocalTime endtime;
	private String courseName;
	private String day;

}
