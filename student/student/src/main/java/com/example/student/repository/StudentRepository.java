package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.Student;
import com.example.student.model.wrapper.StudentDepartmentBranch;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.model.wrapper.TestResults;
import com.example.student.model.wrapper.UpComingTests;
import com.example.student.model.wrapper.findNoOfAttendance;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	public Student findByEmailID(String userName);

	@Query("SELECT new com.example.student.model.wrapper.StudentRoutine(CLAS_S.semester,CLAS_S.time,COURSE.courseName,concat(s.firstName,' ',s.lastName)) "
			+ "FROM\r\n" + " StudentClass sc join	sc.class_Course CLAS_S\r\n"
			+ "	JOIN sc.student s JOIN CLAS_S.course_class COURSE \r\n"
			+ "	 where s.emailID=:email  and trim(to_char(current_date, 'Day'))=CLAS_S.day")
	public List<StudentRoutine> findStudentDetails(@Param("email") String email);

//	@Query("select new com.example.student.model.Student(st.firstName,st.lastName,st.emailID,st.semester,st.user) from Student st join st.courses courses where courses.id=20")
//	public List<Student> findStudentByCourse();

	@Query("select new com.example.student.model.wrapper.findNoOfAttendance(count(*)) " + "from Attendance sc "
			+ "join sc.class_Course cc " + "join sc.student st " + "where st.emailID=:email  and sc.date=CURRENT_DATE "
			+ "and trim(to_char(current_date, 'Day'))=cc.day " + "group by st.emailID ")
	public findNoOfAttendance noOfAttendance(@Param("email") String email);

	@Query("select new com.example.student.model.wrapper.UpComingTests(course.courseName,test.totalMarks,test.date) from TestStudent ts join ts.test test join test.course course join ts.student st  where st.emailID=:email and test.date>CURRENT_DATE order by test.date asc")
	public List<UpComingTests> upComingTests(@Param("email") String email);

	@Query("select new com.example.student.model.wrapper.TestResults(course.courseName,test.totalMarks,ts.marks,((cast(ts.marks as float)/test.totalMarks *100))) from TestStudent ts join ts.test test join test.course course join ts.student st where st.emailID=:email and test.date<CURRENT_DATE")
	public List<TestResults> testResults(@Param("email") String email);
	@Query("select new com.example.student.model.wrapper.StudentDepartmentBranch(concat(st.firstName,' ',st.lastName),dept.dName,branch.branchName) from Student st join st.branch branch join branch.department dept where st.emailID=:email")
	public StudentDepartmentBranch getDepartmentBranch(@Param("email") String email);
	
	@Query("select st from Student st join st.branch branch join branch.department dept where dept.id=:id")
	public List<Student> findStudentByDepartment(@Param("id") long id);
	@Query("select student from Student student join student.class_Courses class where class.class_Course.id=:id")
	public List<Student> findStudentByClassID(@Param("id") long id);

}
