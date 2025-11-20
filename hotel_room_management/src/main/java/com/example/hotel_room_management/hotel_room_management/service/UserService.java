package com.example.hotel_room_management.hotel_room_management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.hotel_room_management.hotel_room_management.model.User;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.UserList;

public interface UserService {
    public User saveUser(User user);
    public Page<UserList> findUsers(int pageNo, int pageSize, String sortField, String sortDirection);
    
	public Page<UserList> userListsFilteredByRole(int pageNo, int pageSize, String sortField, String sortDirection,
			String role);
}
