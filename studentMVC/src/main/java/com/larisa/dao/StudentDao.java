package com.larisa.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.larisa.dto.GetAllStudentData;
import com.larisa.dto.Student;
import com.larisa.dto.UserStudent;

public interface StudentDao {
	public Page<Student> getListOfStudents(Pageable pageable);
	public void addStudent(Student st) throws DuplicateKeyException;
	public void updateStudent(Student st);
	public void deleteStudent(Student st);
	public Student getStudent(long id);
	public Student findStudentByEmail(String email);
	public Student findStudentByPhoneNo(Long phone_no);
	public GetAllStudentData getAllStudentData(long id);
}
