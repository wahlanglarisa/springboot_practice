package com.larisa.dao;

import org.springframework.dao.DuplicateKeyException;

import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;

public interface UserDao {
	public UserRole validateUser(User user);
	public User saveUser(User user) throws DuplicateKeyException;
	public User updateUser(User user);
	public User updateUserPassword(UpdatePassword password);

	public String deleteUser(String userId);
	public User getUserByEmail(String email);
	public String deleteUserByEmail(String email);
	public boolean validatePassword(String email,String Oldpassword);

}
