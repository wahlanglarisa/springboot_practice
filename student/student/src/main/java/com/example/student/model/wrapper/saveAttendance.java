package com.example.student.model.wrapper;

import java.util.List;

import com.example.student.model.Attendance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class saveAttendance {
	private List<Attendance> studentClasses;
}
