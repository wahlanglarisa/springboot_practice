package com.example.student.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.student.model.Student;
import com.example.student.model.StudentDepartmentBranch;
import com.example.student.model.StudentRoutine;
import com.example.student.model.TestResults;
import com.example.student.model.UpComingTests;
import com.example.student.model.UserDto;
import com.example.student.model.findNoOfAttendance;

public interface StudentService {
	public Student saveStudent(UserDto st);
	public List<StudentRoutine> findStudentClasses(String email);
	public findNoOfAttendance noOfAttendance(String email);
	public List<UpComingTests> upComingTests(String email);
	public List<TestResults> testResults(String email);
	public StudentDepartmentBranch getDepartmentBranch(String email);
}
