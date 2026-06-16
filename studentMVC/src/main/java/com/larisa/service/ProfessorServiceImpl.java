package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.ProfessorDao;
import com.larisa.dao.UserDao;
import com.larisa.dto.Professor;
import com.larisa.dto.User;
import com.larisa.dto.UserProfessor;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private UserDao userDao;

	@Override
	public void saveProfessor(UserProfessor userProfessor) {
		// TODO Auto-generated method stub
		User user=new User(userProfessor.getPassword(),userProfessor.getEmail());
		User createdUser=userDao.saveUser(user,"Professor");
		Professor professor=new Professor(userProfessor.getFirstName(),userProfessor.getLastName(),userProfessor.getMiddleName(),createdUser.getUserid(),userProfessor.getPhoneNo());
		professorDao.saveProfessor(professor);
	}

	@Override
	public Professor getByPhoneNumber(Long phoneNo) {
		// TODO Auto-generated method stub
		return professorDao.getByPhoneNumber(phoneNo);
	}

	@Override
	public List<Professor> getProfessors() {
		// TODO Auto-generated method stub
		return professorDao.getProfessors();
	}
	

}
