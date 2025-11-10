package com.example.student.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.student.model.Department;
import com.example.student.model.Professor;
import com.example.student.model.StudentClass;
import com.example.student.model.wrapper.AttendancePage;
import com.example.student.model.wrapper.FindProfessorClasses;
import com.example.student.model.wrapper.ProfListClasses;
import com.example.student.model.wrapper.UserDto;
import com.example.student.model.wrapper.saveAttendance;

public interface ProfessorService {
	public Professor saveProfessor(UserDto userDto);

	public List<FindProfessorClasses> findProfessorClasses(String email);

	public List<ProfListClasses> getClass_Courses(String email);

	public List<AttendancePage> getAttendancePages(String email, long id);

	public String saveAttendance(saveAttendance studentClass);

	public Professor getProfByEmail(String email);

	public Page<ProfListClasses> getDeptClass_Courses(long id,Pageable pageable);

	public List<Professor> getProfessors();

	public List<Professor> findByDepartmentID(long id);

	public Page<ProfListClasses> getProfClass_Courses(String email,Pageable pageable);

	public List<ProfListClasses> getProfClass_Courses_By_Day(String email, String day);

}
