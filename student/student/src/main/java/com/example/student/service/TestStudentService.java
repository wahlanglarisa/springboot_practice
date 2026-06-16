package com.example.student.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.student.model.TestStudent;
import com.example.student.model.wrapper.StudentsTestData;

public interface TestStudentService {
public TestStudent saveTestStudent(List<TestStudent> testStudent);
	public List<StudentsTestData> getStudentByTestID(long id);
}
