package com.larisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.larisa.dao.UserDao;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;
import com.larisa.dto.UserLogin;
import com.larisa.dto.UserRole;
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    System.out.println("User LoadUserByUserName");

	    UserRole userRole = userDao.getUserRoleByEmail(username);

	    if (userRole == null) {
	    	System.out.println("throwing  UsernameNotFoundException");
	        throw new UsernameNotFoundException("User not found: " + username);
	    }

	    System.out.println(userRole);

	    return new UserLogin(userRole);
	}

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
