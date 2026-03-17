package com.larisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.UserDao;
import com.larisa.dao.UserRole;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;
@Service
public class UserServiceImpl implements UserService{
	@Override
	public String deleteUser(String userId) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(userId);
	}
	@Autowired
	private UserDao userDao;
	@Override
	public UserRole validateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.validateUser(user);
	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(email);
	}
	@Override
	public String deleteUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.deleteUserByEmail(email);
	}
	@Override
	public boolean validatePassword(String email, String Oldpassword) {
		// TODO Auto-generated method stub
		return userDao.validatePassword(email, Oldpassword);
	}
	@Override
	public User updateUserPassword(UpdatePassword password) {
		// TODO Auto-generated method stub
		return userDao.updateUserPassword(password);
	}
	

}
