package com.example.hotel_room_management.hotel_room_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel_room_management.hotel_room_management.model.User;
import com.example.hotel_room_management.hotel_room_management.model.UserPrincipal;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.UserList;
import com.example.hotel_room_management.hotel_room_management.repository.RoleRepository;
import com.example.hotel_room_management.hotel_room_management.repository.UserRepository;
import com.example.hotel_room_management.hotel_room_management.service.UserService;
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    	@Lazy

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user=userRepository.findByEmail(username);
        return new UserPrincipal(user);
    }
    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        user.setRole(roleRepository.findByName("Guest"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public Page<UserList> findUsers(int pageNo, int pageSize, String sortField, String sortDirection) {
        	Sort sort;
		if (sortField.equals("roleName")) {
			sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "r.name");
		} else {
			sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
		}
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return userRepository.userLists(pageable);
    }
    	@Override
	public Page<UserList> userListsFilteredByRole(int pageNo, int pageSize, String sortField, String sortDirection,
			String role) {
		// TODO Auto-generated method stub
		Sort sort;
		if (sortField.equals("roleName")) {
			sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "r.name");
		} else {
			sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
		}
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return userRepository.userListsRoleFiltered(pageable, role);
	}

}
