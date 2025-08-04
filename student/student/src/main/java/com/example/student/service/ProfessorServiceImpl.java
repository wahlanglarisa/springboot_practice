package com.example.student.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.Course;
import com.example.student.model.Professor;
import com.example.student.model.User;
import com.example.student.model.UserDto;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.ProfessorRepository;
import com.example.student.model.repository.RoleRepository;
import com.example.student.model.repository.UserRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public Professor saveProfessor(UserDto userDto) {
		// TODO Auto-generated method stu
		User user=new User(userDto.getFirstName(),userDto.getLastName(),userDto.getEmailID(),bCryptPasswordEncoder.encode(userDto.getPassword()),Arrays.asList(roleRepository.findByName("Professor")));
		Professor professor=new Professor(userDto.getFirstName(), userDto.getLastName(), userDto.getEmailID());
		userRepository.save(user);
		for(String course: userDto.getCourses()) {
			Course course1=courseRepository.findByCourseName(course);
		professor.getCourses().add(course1);
		}
		return professorRepository.save(professor);
	}

}
