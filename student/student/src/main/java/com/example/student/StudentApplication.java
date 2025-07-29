package com.example.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.student.model.Course;
import com.example.student.model.Professor;
import com.example.student.model.Student;
import com.example.student.model.StudentRoutine;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.ProfessorRepository;
import com.example.student.model.repository.StudentRepository;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner{

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
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Run function ");
		List<StudentRoutine> objects = studentRepository.findStudentDetails("heyyou456@gmail.com");
//		System.out.println(objects);
		List<Student> students=studentRepository.findStudentByCourse();
		System.out.println("From find students by course function\n\n");
		for(Student student:students) {
			System.out.println(student.getEmailID()+" "+student.getFirstName()+" "+student.getLastName());

		}
		System.out.println("From find students details function\n\n");

		for(StudentRoutine st_rout:objects) {
			System.out.println(st_rout.getSemester()+" "+st_rout.getCourseName()+" "+st_rout.getTime()+" "+st_rout.getStudentName());

		}


	}

}
