package com.example.student.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.student.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	public Student findByEmailID(String userName);
	@Query("SELECT\r\n"
			+ "\r\n"
			+ "	CLAS_S.semester,\r\n"
			+ "	\"time\",\r\n"
			+ "	COURSE.courseName\r\n"
			+ "FROM\r\n"
			+ "	Class_Course CLAS_S\r\n"
			+ "	INNER JOIN Course COURSE ON CLAS_S.course_class.id = COURSE.id\r\n"
			+ "	INNER JOIN CLAS_S.students s INNER JOIN Student ST on s.id=ST.id  where ST.emailID='heyyou456@gmail.com'  ")
	public List<Object> findStudentDetails();
}
  