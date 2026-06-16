package com.larisa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.larisa.dto.Course;
import com.larisa.dto.Student;

public interface CourseDao {
	public List<Course> getCourses();
	
	public Page<Course> getCoursesByStudent(Student student,Pageable pageable);
	public List<Course> getCoursesNotTakenByStudent(Student student);
}
