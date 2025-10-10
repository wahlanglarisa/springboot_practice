package com.example.student.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.student.model.Student;
import com.example.student.model.wrapper.StudentDepartmentBranch;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.model.wrapper.TestResults;
import com.example.student.model.wrapper.UpComingTests;
import com.example.student.model.wrapper.UserDto;
import com.example.student.model.wrapper.findNoOfAttendance;

public interface StudentService {
	public Student saveStudent(UserDto st);

	public List<StudentRoutine> findStudentClasses(String email);

	public findNoOfAttendance noOfAttendance(String email);

	public List<UpComingTests> upComingTests(String email);

	public List<TestResults> testResults(String email);

	public StudentDepartmentBranch getDepartmentBranch(String email);

	public List<Student> getStudentByDepartment(long id);

	public List<Student> findStudentByClassID(long id);

	public Student findStudentByEmailId(String email);

	public List<StudentRoutine> getStudentRoutines(String email,String day);

}
