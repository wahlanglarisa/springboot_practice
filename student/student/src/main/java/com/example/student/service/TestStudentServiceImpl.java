package com.example.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.TestStudent;
import com.example.student.model.wrapper.StudentsTestData;
import com.example.student.repository.TestStudentRepository;
@Service
public class TestStudentServiceImpl implements TestStudentService {
	@Autowired
	TestStudentRepository testStudentRepository;
	@Override
	public TestStudent saveTestStudent(List<TestStudent> testStudent) {
		// TODO Auto-generated method stub
		TestStudent testStudent2=null;
		for (TestStudent testStudent3 : testStudent) {
			testStudent2=testStudentRepository.save(testStudent3);
		}
		return testStudent2;
	}
	@Override
	public List<StudentsTestData> getStudentByTestID(long id) {
		// TODO Auto-generated method stub
		return testStudentRepository.getStudentByTestID(id);
	}

}
