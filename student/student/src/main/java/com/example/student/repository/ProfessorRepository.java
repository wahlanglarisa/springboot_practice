package com.example.student.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.Class_Course;
import com.example.student.model.Department;
import com.example.student.model.Professor;
import com.example.student.model.wrapper.AttendancePage;
import com.example.student.model.wrapper.FindProfessorClasses;
import com.example.student.model.wrapper.ProfListClasses;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	@Query("select new com.example.student.model.wrapper.FindProfessorClasses(course.courseName,count(*)) "
			+ "from Professor prof "
			+ " join prof.class_Course class join class.course course where prof.email=:email and "
			+ "trim(to_char(current_date, 'Day'))=class.day group by course.courseName")
	public List<FindProfessorClasses> findProfessorClasses(@Param("email") String email); 
	@Query("select new com.example.student.model.wrapper.ProfListClasses(class.id,course.courseName,ct.startTime,ct.endTime,class.day,course.id,concat(prof.firstName,' ',prof.lastName)) "
			+ "from Professor prof "
			+ "join prof.class_Course class "
			+ " join class.course course "+
			"join class.classTime ct "
			+ "where prof.email=:email and"
			+ " trim(to_char(current_date, 'Day'))=class.day and current_time<ct.startTime")
	public List<ProfListClasses> getClass_Courses(@Param("email") String email);
	@Query("select distinct new com.example.student.model.wrapper.AttendancePage(concat(student.firstName,' ',student.lastName),student.semester,course.id,student.id,class.id)"
			+ " from Professor prof"
			+ " join prof.class_Course class"
			+ " join class.students st "
			+ "join class.course course"
			+ " join st.student student where prof.email=:email and class.id=:id")
	public List<AttendancePage> getAttendancePages(@Param("email") String email,@Param("id") long id);
	@Query("select new  com.example.student.model.wrapper.ProfListClasses(class.id,course.courseName,ct.startTime,ct.endTime,class.day,course.id,concat(class.professor.firstName,' ',class.professor.lastName))"
			+ " from Class_Course class "
			+ "join class.course course join course.department dept join class.classTime ct left join class.professor where dept.id=:id")
	public Page<ProfListClasses> getDeptClass_Courses(@Param("id") long id,Pageable pageable);
	public Professor findByEmail(String email);
	@Query("select distinct prof from Professor prof " +
		       "join prof.departments dept " +
		       "left join fetch prof.class_Course " +
		       "where dept.id = :id")
	public List<Professor> findByDepartmentID(@Param("id") long id);
	@Query("select new com.example.student.model.wrapper.ProfListClasses(class.id,course.courseName,ct.startTime,ct.endTime,class.day,course.id,concat(prof.firstName,' ',prof.lastName)) "
			+ "from Professor prof "
			+ "join prof.class_Course class "
			+ " join class.course course join class.classTime ct "
			+ "where prof.email=:email")
	public List<ProfListClasses> getProfClass_Courses(@Param("email") String email);
		@Query("select new com.example.student.model.wrapper.ProfListClasses(class.id,course.courseName,ct.startTime,ct.endTime,class.day,course.id,concat(prof.firstName,' ',prof.lastName)) "
			+ "from Professor prof "
			+ "join prof.class_Course class "
			+ " join class.course course join class.classTime ct "
			+ "where prof.email=:email and class.day=:day")
	public List<ProfListClasses> getProfClass_Courses_By_Day(@Param("email") String email,@Param("day") String day);
}

 