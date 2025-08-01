package com.example.student.service;

import java.util.List;

import com.example.student.model.User;
import com.example.student.model.UserList;

public interface UserService {
	public List<User> getUsers();
	public List<UserList> userLists();
	public User getUserById(long id);
	public User updateUser(User user);
}
