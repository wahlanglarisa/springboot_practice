package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.Student;
import com.example.student.model.TestStudent;
import com.example.student.model.TestStudentID;
import com.example.student.model.wrapper.StudentsTestData;
@Repository
public interface TestStudentRepository extends JpaRepository<TestStudent,String> {
	@Query("select new com.example.student.model.wrapper.StudentsTestData(teststudent.testStudentID,test.id,student.ID,test.course.courseName,concat(student.firstName,' ',student.lastName )) from TestStudent teststudent join teststudent.student student join teststudent.test test join test.course where test.id=:id")
	public List<StudentsTestData> getStudentByTestID(@Param("id") long id);
}
