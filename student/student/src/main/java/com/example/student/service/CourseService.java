package com.example.student.service;

import java.util.Collection;
import java.util.List;

import com.example.student.model.Course;
import com.example.student.model.Department;
import com.example.student.model.Professor;

public interface CourseService {
	public Collection<String> findCourses();
	public Course findById(long id);
	public List<Course> findAllCourses();
	public List<Course> findbyDepartment(Department department);
	public List<Course> findByProfessor(long id);

}
