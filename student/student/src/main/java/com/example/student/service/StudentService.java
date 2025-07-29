package com.example.student.service;

import java.util.List;

import com.example.student.model.Student;
import com.example.student.model.StudentRoutine;
import com.example.student.model.UserDto;
import com.example.student.model.findNoOfAttendance;

public interface StudentService {
	public Student saveStudent(UserDto st);
	public List<StudentRoutine> findStudentClasses(String email);
	public findNoOfAttendance noOfAttendance(String email);
}
