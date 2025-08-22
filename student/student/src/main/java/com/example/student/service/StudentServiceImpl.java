package com.example.student.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.Course;
import com.example.student.model.Role;
import com.example.student.model.Student;
import com.example.student.model.StudentDepartmentBranch;
import com.example.student.model.StudentRoutine;
import com.example.student.model.TestResults;
import com.example.student.model.UpComingTests;
import com.example.student.model.User;
import com.example.student.model.UserDto;
import com.example.student.model.findNoOfAttendance;
import com.example.student.model.repository.BranchRepository;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.RoleRepository;
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
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Override
	public Student saveStudent(UserDto st) {
		
		User user=new User(st.getFirstName(),st.getLastName(),st.getEmailID(),bCryptPasswordEncoder.encode(st.getPassword()),Arrays.asList(roleRepository.findByName("Student")));
		Student st1=new Student(st.getFirstName(), st.getLastName(), st.getEmailID(), st.getSemester(), user);

		for (String course : st.getCourses()) {
			Course course2=courseRepository.findByCourseName(course);
			st1.getCourses().add(course2);
		}
		st1.setBranch(branchRepository.getReferenceById(st.getBranch_id()));
		userRepository.save(user);
		return studentRepository.save(st1);
	}
	@Override
	public List<StudentRoutine> findStudentClasses(String email) {
		// TODO Auto-generated method stub
		return studentRepository.findStudentDetails(email);
	}
	@Override
	public findNoOfAttendance noOfAttendance(String email) {
		// TODO Auto-generated method stub
		findNoOfAttendance attendance=studentRepository.noOfAttendance(email);
		if(attendance==null) {
			findNoOfAttendance attendance2=new findNoOfAttendance(0);
			return attendance2;
		}
		return studentRepository.noOfAttendance(email);
	}
	@Override
	public List<UpComingTests> upComingTests(String email) {
		// TODO Auto-generated method stub
		return studentRepository.upComingTests(email);
	}
	@Override
	public List<TestResults> testResults(String email) {
		// TODO Auto-generated method stub
		return studentRepository.testResults(email);
	}
	@Override
	public StudentDepartmentBranch getDepartmentBranch(String email) {
		// TODO Auto-generated method stub
		return studentRepository.getDepartmentBranch(email);
	}
	
}
