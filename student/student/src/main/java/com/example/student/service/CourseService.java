package com.example.student.service;

import java.util.Collection;
import java.util.List;

import com.example.student.model.Branch;
import com.example.student.model.Course;
import com.example.student.model.Department;
import com.example.student.model.Professor;

public interface CourseService {
	public Collection<String> findCourses();
	public Course findById(long id);
	public List<Course> findAllCourses();
	public List<Course> findbyDepartment(Department department);
	public List<Course> findByProfessor(long id);
		public List<String> findbyDepartmentID(long id);
	public Course addCourse(Course course);
	public List<Course> findByBranchAndSemester(Branch branch,Long semester);

}
