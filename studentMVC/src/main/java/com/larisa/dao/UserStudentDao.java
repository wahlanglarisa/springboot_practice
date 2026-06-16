package com.larisa.dao;

import com.larisa.dto.UserStudent;

public interface UserStudentDao {
	public UserStudent getUserStudent(long id);
	public UserStudent getUserStudentbyEmail(String email);

}
