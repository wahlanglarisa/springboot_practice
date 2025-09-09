package com.example.student.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.AttendancePage;
import com.example.student.model.Class_Course;
import com.example.student.model.Department;
import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.model.Professor;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	@Query("select new com.example.student.model.FindProfessorClasses(course.courseName,count(*)) "
			+ "from Professor prof "
			+ " join prof.class_Course class join class.course_class course where prof.email=:email and "
			+ "trim(to_char(current_date, 'Day'))=class.day group by course.courseName")
	public List<FindProfessorClasses> findProfessorClasses(@Param("email") String email); 
	@Query("select new com.example.student.model.ProfListClasses(class.id,course.courseName,class.time,class.day) "
			+ "from Professor prof "
			+ "join prof.class_Course class "
			+ " join class.course_class course "
			+ "where prof.email=:email and"
			+ " trim(to_char(current_date, 'Day'))=class.day and current_time<class.time")
	public List<ProfListClasses> getClass_Courses(@Param("email") String email);
	@Query("select distinct new com.example.student.model.AttendancePage(concat(student.firstName,' ',student.lastName),student.semester,course.id,student.id,class.id)"
			+ " from Professor prof"
			+ " join prof.class_Course class"
			+ " join class.students st "
			+ "join class.course_class course"
			+ " join st.student student where prof.email=:email and class.id=:id")
	public List<AttendancePage> getAttendancePages(@Param("email") String email,@Param("id") long id);
	@Query("select new  com.example.student.model.ProfListClasses(class.id,course.courseName,class.time,class.day)"
			+ " from Class_Course class "
			+ "join class.course_class course join course.department dept where dept.id=:id")
	public List<ProfListClasses> getDeptClass_Courses(@Param("id") long id);
	public Professor findByEmail(String email);
	@Query("select distinct prof from Professor prof " +
		       "join prof.departments dept " +
		       "left join fetch prof.class_Course " +
		       "where dept.id = :id")
	public List<Professor> findByDepartmentID(@Param("id") long id);
}
