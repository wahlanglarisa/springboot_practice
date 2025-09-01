package com.example.student.service;

import java.util.Collection;
import java.util.List;

import com.example.student.model.Course;

public interface CourseService {
	public Collection<String> findCourses();
	public Course findById(long id);
	public List<Course> findAllCourses();

}
