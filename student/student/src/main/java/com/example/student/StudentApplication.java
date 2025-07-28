package com.example.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.student.model.Course;
import com.example.student.model.Professor;
import com.example.student.model.Student;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.ProfessorRepository;
import com.example.student.model.repository.StudentRepository;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(courseRepository.listofCourses());
//		
//		
//	}
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Run function ");
//		List<Object[]> objects = studentRepository.findStudentDetails();
//		for (Object[] ob : objects) {
//			System.out.println(ob[2]);
//
//		}
//	}

}
