package com.example.student.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.student.model.User;
import com.example.student.model.wrapper.UserList;

public interface UserService {
	public List<User> getUsers();
	public Page<UserList> userLists(int pageNo,int pageSize,String sortField,String sortDirection);
		public List<UserList> userListsFilteredByRole(int pageNo,int pageSize,String sortField,String sortDirection,String role);

	public User getUserById(long id);
	public User updateUser(User user);
	public void deleteUser(long id);
}
