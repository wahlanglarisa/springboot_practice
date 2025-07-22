package com.example.student.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.Course;
import com.example.student.model.Role;
import com.example.student.model.Student;
import com.example.student.model.User;
import com.example.student.model.UserDto;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.StudentRepository;
import com.example.student.model.repository.UserRepository;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Override
	public Student saveStudent(UserDto st) {
		
		User user=new User(st.getFirstName(),st.getLastName(),st.getEmailID(),bCryptPasswordEncoder.encode(st.getPassword()),Arrays.asList(new Role("Student")));
		Student st1=new Student(st.getFirstName(), st.getLastName(), st.getEmailID(), st.getSemester(), user);

		for (String course : st.getCourses()) {
			Course course2=courseRepository.findByCourseName(course);
			st1.getCourses().add(course2);
		}
		userRepository.save(user);
		return studentRepository.save(st1);
	}
	
}
