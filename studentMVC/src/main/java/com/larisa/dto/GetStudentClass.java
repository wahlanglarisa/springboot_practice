package com.larisa.dto;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class GetStudentClass {
	private String courseName,day,profName;
	@DateTimeFormat(pattern="HH:mm")
	private LocalTime startTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;
	private String startTime12hr,endTime12hr;
	public GetStudentClass(String courseName, String day, String profName, LocalTime startTime, LocalTime endTime) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.getDefault());
		String starttime12Hour = startTime.format(formatter);
		String endtime12Hour = endTime.format(formatter);

		this.courseName = courseName;
		this.day = day;
		this.profName = profName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startTime12hr=starttime12Hour;
		this.endTime12hr=endtime12Hour;
		
		
	}
	
}
