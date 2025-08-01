package com.example.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.student.model.Course;
import com.example.student.model.Professor;
import com.example.student.model.Student;
import com.example.student.model.StudentRoutine;
import com.example.student.model.User;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.ProfessorRepository;
import com.example.student.model.repository.RoleRepository;
import com.example.student.model.repository.StudentRepository;
import com.example.student.model.repository.UserRepository;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private RoleRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(courseRepository.listofCourses());
//		
//		
//	}
	@Override
	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(repository.findByName("Admin"));
//		User user=new User("Larisa","Wahlang","admin@gmail.com",bCryptPasswordEncoder.encode("admin123"),Arrays.asList(repository.findByName("Admin")));
//		userRepository.save(user);

	}

}
