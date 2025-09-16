package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.User;
import com.example.student.model.UserList;
import com.example.student.model.UserPrincipal;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	@Lazy
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user1 = userRepository.findByEmail(username);
		System.out.println(userRepository.findByEmail(username) + " " + username);
		System.out.println("In load by username function" + user1);
		System.out.println(
				user1.getEmail() + " " + user1.getFirstName() + " " + user1.getLastName() + " " + user1.getPassword());
		return new UserPrincipal(user1);

	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public List<UserList> userLists() {
		// TODO Auto-generated method stub
		return userRepository.userLists();
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isEmpty()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public User updateUser(User updatedUser) {
		// TODO Auto-generated method stub
		User existingUser = userRepository.findById(updatedUser.getId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		// Update user fields
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setEmail(updatedUser.getEmail());
		if (updatedUser.getPassword() != "") {
			existingUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
		}
		else {
			existingUser.setPassword(existingUser.getPassword());

		}
		// Safely update roles (replace only if non-null)
		if (updatedUser.getRoles() != null) {
			existingUser.getRoles().clear();
			existingUser.getRoles().addAll(updatedUser.getRoles());
		}

		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		;
	}
}
