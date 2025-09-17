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
	
		private String testStudentID; 


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StudentClassId))
			return false;
		TestStudentID that = (TestStudentID) o;
		return Objects.equals(testStudentID,that.testStudentID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(testStudentID);
	}

}
