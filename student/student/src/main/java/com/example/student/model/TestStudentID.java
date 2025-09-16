package com.example.student.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestStudentID implements Serializable {
	private long test;
	private Long student;
	private long marks;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StudentClassId))
			return false;
		TestStudentID that = (TestStudentID) o;
		return Objects.equals(student, that.student) && Objects.equals(test, that.student)
				&& Objects.equals(test, that.marks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(test, student,marks);
	}

}
