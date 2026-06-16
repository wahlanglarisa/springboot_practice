package com.example.student.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendancePage {

	private String StudentName;
	private long semester;
	private long courseID;
	private long st_id;
	private long class_id;
}
