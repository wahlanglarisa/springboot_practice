package com.larisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.UserStudentDao;
import com.larisa.dto.UserStudent;
@Service
public class UserStudentServiceImpl implements UserStudentService {
	@Override
	public UserStudent getUserStudentbyEmail(String email) {
		// TODO Auto-generated method stub
		return studentDao.getUserStudentbyEmail(email);
	}
	@Autowired
	private UserStudentDao studentDao;
	@Override
	public UserStudent getUserStudent(long id) {
		// TODO Auto-generated method stub
		return studentDao.getUserStudent(id);
	}

}
