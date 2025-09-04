package com.example.student.model.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.student.model.Course;
import com.example.student.model.Department;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	public Course findByCourseName(String CourseName);
	@Query("SELECT c.courseName FROM Course c\r\n"
			+ "ORDER BY id ASC")
	public Collection<String> listofCourses();
	public List<Course> findByDepartment(Department department);
	
}
 