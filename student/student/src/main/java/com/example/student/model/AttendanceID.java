package com.example.student.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceID implements Serializable {
	private Long student;
	private Long class_Course;
	private Date date;
	private Time time;

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StudentClassId))
			return false;
		AttendanceID that = (AttendanceID) o;
		return Objects.equals(student, that.student) && Objects.equals(class_Course, that.class_Course)
				&& Objects.equals(date, that.date) && Objects.equals(time, that.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(student, class_Course, date,time);
	}

}
