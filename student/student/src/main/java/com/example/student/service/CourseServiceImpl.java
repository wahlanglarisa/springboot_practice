package com.example.student.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.repository.CourseRepository;



@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	@Override
	public Collection<String> findCourses() {
		// TODO Auto-generated method stub
		return courseRepository.listofCourses();
	}
	

}
