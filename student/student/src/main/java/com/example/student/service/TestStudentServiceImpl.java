package com.example.student.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.student.model.TestStudent;
import com.example.student.repository.TestStudentRepository;

public class TestStudentServiceImpl implements TestStudentService {
	@Autowired
	TestStudentRepository testStudentRepository;
	@Override
	public TestStudent saveTestStudent(TestStudent testStudent) {
		// TODO Auto-generated method stub
		return testStudentRepository.save(testStudent);
	}

}
