package com.example.student.service;

import com.example.student.model.Professor;
import com.example.student.model.UserDto;

public interface ProfessorService {
	public Professor saveProfessor(UserDto userDto);
}
