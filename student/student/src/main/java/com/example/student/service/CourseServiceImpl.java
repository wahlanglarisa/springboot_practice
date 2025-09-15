package com.example.student.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Course;
import com.example.student.model.Department;
import com.example.student.model.Professor;
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
	@Override
	public Course findById(long id) {
		// TODO Auto-generated method stub
		return courseRepository.getReferenceById(id);
	}
	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}
	@Override
	public List<Course> findbyDepartment(Department department) {
		// TODO Auto-generated method stub
		return courseRepository.findByDepartment(department);
	}
	@Override
	public List<Course> findByProfessor(long id) {
		// TODO Auto-generated method stub
		return courseRepository.findCoursesByProfID(id);
	}
	

}
