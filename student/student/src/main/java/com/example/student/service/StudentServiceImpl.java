package com.example.student.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.Class_Course;
import com.example.student.model.Course;
import com.example.student.model.Role;
import com.example.student.model.Student;
import com.example.student.model.StudentClass;
import com.example.student.model.User;
import com.example.student.model.wrapper.StudentDepartmentBranch;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.model.wrapper.TestResults;
import com.example.student.model.wrapper.UpComingTests;
import com.example.student.model.wrapper.UserDto;
import com.example.student.model.wrapper.findNoOfAttendance;
import com.example.student.repository.BranchRepository;
import com.example.student.repository.ClassRepository;
import com.example.student.repository.CourseRepository;
import com.example.student.repository.RoleRepository;
import com.example.student.repository.StudentClassRepository;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.UserRepository;

@Service
public class StudentServiceImpl implements StudentService {
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
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private StudentClassRepository studentClassRepository;

	@Override
	public Student saveStudent(UserDto st) {

		User user = new User(st.getFirstName(), st.getLastName(), st.getEmailID(),
				bCryptPasswordEncoder.encode(st.getPassword()), Arrays.asList(roleRepository.findByName("Student")));

		User savedUser = userRepository.save(user);

		Student st1 = new Student(st.getFirstName(), st.getLastName(), st.getEmailID(), st.getSemester(), savedUser,
				branchRepository.getReferenceById(st.getBranch_id()));
		Student savedStudent = studentRepository.save(st1);
		List<Course> courses = courseRepository.findByBranchAndSemester(st1.getBranch(), st1.getSemester());
		for (Course course : courses) {
			List<Class_Course> class_Courses = classRepository.findByCourseAndSemesterAndBranch(course,
					savedStudent.getSemester(), savedStudent.getBranch());
			for (Class_Course class_Course : class_Courses) {
				StudentClass studentClass = new StudentClass(savedStudent, class_Course);
				studentClassRepository.save(studentClass);
			}
		}

		return savedStudent;
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
		findNoOfAttendance attendance = studentRepository.noOfAttendance(email);
		if (attendance == null) {
			findNoOfAttendance attendance2 = new findNoOfAttendance(0);
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

	@Override
	public List<StudentRoutine> getStudentRoutines(String email, String day, Long semester, Long branchid) {
		// TODO Auto-generated method stub
		return studentRepository.getStudentRoutines(email, day, semester, branchid);
	}

}
