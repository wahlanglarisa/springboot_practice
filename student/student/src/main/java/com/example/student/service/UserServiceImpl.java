package com.example.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.student.model.User;
import com.example.student.model.UserPrincipal;
import com.example.student.model.repository.StudentRepository;
import com.example.student.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user1 = userRepository.findByEmail(username);
		System.out.println(studentRepository.findByEmailID(username)+" "+username);
		System.out.println("In load by username function" + user1);
//		System.out.println(user1.getEmail()+" "+user1.getFirstName()+" "+user1.getLastName()+" "+user1.getPassword());
		return new UserPrincipal(user1);
	}
}
