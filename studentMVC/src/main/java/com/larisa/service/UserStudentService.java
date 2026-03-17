package com.larisa.service;

import com.larisa.dto.UserStudent;

public interface UserStudentService {
	public UserStudent getUserStudent(long id);
	public UserStudent getUserStudentbyEmail(String email);


}
