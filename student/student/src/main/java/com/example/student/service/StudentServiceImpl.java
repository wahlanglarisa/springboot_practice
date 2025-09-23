package com.example.student.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.Course;
import com.example.student.model.Role;
import com.example.student.model.Student;
import com.example.student.model.User;
import com.example.student.model.wrapper.StudentDepartmentBranch;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.model.wrapper.TestResults;
import com.example.student.model.wrapper.UpComingTests;
import com.example.student.model.wrapper.UserDto;
import com.example.student.model.wrapper.findNoOfAttendance;
import com.example.student.repository.BranchRepository;
import com.example.student.repository.CourseRepository;
import com.example.student.repository.RoleRepository;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.UserRepository;

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
		Student st1=new Student(st.getFirstName(), st.getLastName(), st.getEmailID(), st.getSemester(), user,branchRepository.getReferenceById(st.getBranch_id()));

		for (String course : st.getCourses()) {
			Course course2=courseRepository.findByCourseName(course);
			st1.getCourses().add(course2);
		}
		userRepository.save(user);
		return studentRepository.save(st1);
	}
	@Override
	public List<Student> getStudentByDepartment(long id) {
		// TODO Auto-generated method stub
		return studentRepository.findStudentByDepartment(id);
	}
	@Override
	public List<Student> findStudentByClassID(long id) {
		// TODO Auto-generated method stub
		return studentRepository.findStudentByClassID(id);
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
	@Override
	public Student findStudentByEmailId(String email) {
		// TODO Auto-generated method stub
		return studentRepository.findByEmailID(email);
	}
	
}
