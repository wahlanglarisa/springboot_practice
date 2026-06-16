package com.larisa.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.larisa.dto.Professor;

public interface ProfessorDao {
 public void saveProfessor(Professor professor) throws DuplicateKeyException;
 public Professor getByPhoneNumber(Long phoneNo);
 public List<Professor> getProfessors();
}
