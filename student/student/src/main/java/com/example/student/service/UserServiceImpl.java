package com.example.student.service;

import java.util.List;
import java.util.Optional;

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

import com.example.student.model.Department;
import com.example.student.model.Professor;
import com.example.student.model.Role;
import com.example.student.model.Student;
import com.example.student.model.User;
import com.example.student.model.UserPrincipal;
import com.example.student.model.wrapper.UserList;
import com.example.student.repository.DepartmentRepository;
import com.example.student.repository.ProfessorRepository;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	@Lazy
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

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
	public Page<UserList> userLists(int pageNo, int pageSize, String sortField, String sortDirection) {
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
		} else {
			existingUser.setPassword(existingUser.getPassword());

		}
		// Safely update roles (replace only if non-null)
		if (updatedUser.getRoles() != null) {
			existingUser.getRoles().clear();
			existingUser.getRoles().addAll(updatedUser.getRoles());
		}
		for(Role role :existingUser.getRoles()){
			if(role.getName().equals("Student")){
				existingUser.getStudent().setFirstName(updatedUser.getFirstName());
				existingUser.getStudent().setLastName(updatedUser.getLastName());
				existingUser.getStudent().setEmailID(updatedUser.getEmail());

			}
			if(role.getName().equals("Professor") || role.getName().equals("Head Of Department")){
				existingUser.getProfessor().setFirstName(updatedUser.getFirstName());
				existingUser.getProfessor().setLastName(updatedUser.getLastName());
				existingUser.getProfessor().setEmail(updatedUser.getEmail());
			}
		}

		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated
		User user = userRepository.getReferenceById(id);
		System.out.println("Before Delete " + user.getProfessor());

		try {
			userRepository.delete(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Caught exception "+e.getMessage());
			List<Role> roles = (List<Role>) user.getRoles();
			System.out.println(roles.get(0).getName());
			if (roles.get(0).getName().equals("Head Of Department")) {
				Professor professor = professorRepository.getReferenceById(user.getProfessor().getID());
				Department department = professor.getDepartment();
				System.out.println(department);
				if (department != null) {
					System.out.println("Removing HOD Privileges");
					department.setProfessor(null);
					departmentRepository.save(department);
				}
				user.setProfessor(null);
				professorRepository.delete(professor);
			} else if (roles.get(0).getName().equals("Professor")) {
				Professor professor = professorRepository.getReferenceById(user.getProfessor().getID());
				professor.setClass_Course(null);
				professor.setCourses(null);
				professor.setDepartments(null);
				professor.setTests(null);
				user.setProfessor(null);
				professorRepository.delete(professor);
			}
			userRepository.delete(user);

		}
	}

	@Override
	public List<UserList> userListsFilteredByRole(int pageNo, int pageSize, String sortField, String sortDirection,
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
		return userRepository.userListsRoleFiltered(pageable,role);		
	}
}
