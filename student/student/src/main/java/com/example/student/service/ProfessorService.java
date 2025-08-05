package com.example.student.service;


import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.model.Professor;
import com.example.student.model.UserDto;

public interface ProfessorService {
	public Professor saveProfessor(UserDto userDto);
	public List<FindProfessorClasses> findProfessorClasses(String email);
	public List<ProfListClasses> getClass_Courses(String email);
}
