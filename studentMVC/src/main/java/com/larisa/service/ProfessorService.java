package com.larisa.service;

import java.util.List;

import com.larisa.dto.Professor;
import com.larisa.dto.UserProfessor;

public interface ProfessorService {
	public void saveProfessor(UserProfessor userProfessor);
	 public Professor getByPhoneNumber(Long phoneNo);
		public List<Professor> getProfessors();

}
