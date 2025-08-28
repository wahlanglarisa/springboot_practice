package com.example.student.service;


import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.student.model.AttendancePage;
import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.model.Professor;
import com.example.student.model.StudentClass;
import com.example.student.model.UserDto;
import com.example.student.model.saveAttendance;

public interface ProfessorService {
	public Professor saveProfessor(UserDto userDto);
	public List<FindProfessorClasses> findProfessorClasses(String email);
	public List<ProfListClasses> getClass_Courses(String email);
	public List<AttendancePage> getAttendancePages(String email,long id);
	public String saveAttendance(saveAttendance studentClass);
	public Professor getProfByEmail(String email);
	public List<ProfListClasses> getDeptClass_Courses(long id);
}
