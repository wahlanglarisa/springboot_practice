package com.example.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Test;
import com.example.student.model.repository.TestRepository;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestRepository testRepository;
	@Override
	public Test createtest(Test test) {
		// TODO Auto-generated method stub
		return testRepository.save(test);
	}

}
