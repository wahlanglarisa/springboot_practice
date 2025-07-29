package com.example.student.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.Student;
import com.example.student.model.StudentRoutine;
import com.example.student.model.findNoOfAttendance;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	public Student findByEmailID(String userName);
	@Query("SELECT new com.example.student.model.StudentRoutine(CLAS_S.semester,CLAS_S.time,COURSE.courseName,concat(s.firstName,' ',s.lastName)) "
			+ "FROM\r\n"
			+ "	Class_Course CLAS_S\r\n"
			+ "	JOIN CLAS_S.students s JOIN CLAS_S.course_class COURSE \r\n"
			+ "	 where s.emailID=:email  ")
	public List<StudentRoutine> findStudentDetails(@Param("email") String email);
	@Query("select new com.example.student.model.Student(st.firstName,st.lastName,st.emailID,st.semester,st.user) from Student st join st.courses courses where courses.id=20")
	public List <Student> findStudentByCourse();
	
	@Query("select new com.example.student.model.findNoOfAttendance(count(*)) from Class_Course cc join cc.students st where st.emailID=:email group by st.emailID")
	public findNoOfAttendance noOfAttendance(@Param("email") String email);
	
}
   