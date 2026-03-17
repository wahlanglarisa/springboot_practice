package com.larisa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.larisa.dto.GetAllStudentData;
import com.larisa.dto.Student;
import com.larisa.dto.UserStudent;

public interface StudentService {
	public Page<Student> getListOfStudents(Pageable pageable);
	public void addStudent(UserStudent st);
	public void updateStudent(UserStudent st);
	public void deleteStudent(Student st);
	public Student getStudent(long id);
	public Student findStudentByEmail(String email);
	public Student findStudentByPhoneNo(Long phone_no);
	public GetAllStudentData getAllStudentData(long id);

}
