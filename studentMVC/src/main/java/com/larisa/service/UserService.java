package com.larisa.service;

import com.larisa.dao.UserRole;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;

public interface UserService {
	public UserRole validateUser(User user);
	public String deleteUser(String userId);
	public User getUserByEmail(String email);
	public String deleteUserByEmail(String email);
	public boolean validatePassword(String email,String Oldpassword);
	public User updateUserPassword(UpdatePassword password);

}
