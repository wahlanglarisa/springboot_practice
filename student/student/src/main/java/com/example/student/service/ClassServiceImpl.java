package com.example.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Class_Course;
import com.example.student.model.repository.ClassRepository;
@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassRepository classRepository;
	
	@Override
	public Class_Course savClass_Course(Class_Course class_Course) {
		// TODO Auto-generated method stub
		return classRepository.save(class_Course);
	}

}
