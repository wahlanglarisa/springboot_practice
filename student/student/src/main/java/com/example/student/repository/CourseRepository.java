package com.example.student.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.Course;
import com.example.student.model.Department;
import com.example.student.model.Professor;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	public Course findByCourseName(String CourseName);
	@Query("SELECT c.courseName FROM Course c\r\n"
			+ "ORDER BY id ASC")
	public Collection<String> listofCourses();
	public List<Course> findByDepartment(Department department);
	@Query("select c from Course c join c.professors prof where prof.ID=:id")
	public List<Course> findCoursesByProfID(@Param("id") long id );
	@Query("select c.courseName from Course c join c.department dept where dept.id=:id")
	public List<String> findCoursesByDepartmentID(@Param("id") long id);
}
 