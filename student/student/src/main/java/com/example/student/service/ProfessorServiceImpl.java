package com.example.student.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.model.Attendance;
import com.example.student.model.AttendancePage;
import com.example.student.model.Course;
import com.example.student.model.Department;
import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.model.Professor;
import com.example.student.model.StudentClass;
import com.example.student.model.User;
import com.example.student.model.UserDto;
import com.example.student.model.saveAttendance;
import com.example.student.model.repository.AttendanceRepository;
import com.example.student.model.repository.CourseRepository;
import com.example.student.model.repository.DepartmentRepository;
import com.example.student.model.repository.ProfessorRepository;
import com.example.student.model.repository.RoleRepository;
import com.example.student.model.repository.StudentClassRepository;
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
	@Autowired
	private StudentClassRepository classRepository;
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public Professor saveProfessor(UserDto userDto) {
		// TODO Auto-generated method stu
		User user=new User(userDto.getFirstName(),userDto.getLastName(),userDto.getEmailID(),bCryptPasswordEncoder.encode(userDto.getPassword()),Arrays.asList(roleRepository.findByName(userDto.isHod()?"Head Of Department":"Professor")));
		Professor professor=new Professor(userDto.getFirstName(), userDto.getLastName(), userDto.getEmailID());
		Department department=departmentRepository.getById(userDto.getDept_id());
		System.out.println(userDto.isHod());
		
		professor.setDepartments(department);
		professor.setUser(user);
		userRepository.save(user);
		for(String course: userDto.getCourses()) {
			Course course1=courseRepository.findByCourseName(course);
		professor.getCourses().add(course1);
		}
		Professor professor2= professorRepository.save(professor);
		if(userDto.isHod()) {
			department.setProfessor(professor);
			departmentRepository.save(department);
		} 
		return professor2;
	}
	@Override
	public List<FindProfessorClasses> findProfessorClasses(String email) {
		// TODO Auto-generated method stub
		return professorRepository.findProfessorClasses(email);
	}
	@Override
	public List<ProfListClasses> getClass_Courses(String email) {
		// TODO Auto-generated method stub
		return professorRepository.getClass_Courses(email);
	}
	@Override
	public List<AttendancePage> getAttendancePages(String email,long id) {
		// TODO Auto-generated method stub
		return professorRepository.getAttendancePages(email,id);
	}
	@Override
	public String saveAttendance(saveAttendance studentClass) {
		// TODO Auto-generated method stub
		System.out.println("in save attendance function");
		for(Attendance studentClass2:studentClass.getStudentClasses()) {
			if(studentClass2.getStudent().getID()!=0) {
				System.out.println(studentClass2.getClass_Course().getId());
				studentClass2.setDate(new Date(System.currentTimeMillis()));
				studentClass2.setTime(new Time(new java.util.Date().getTime()));
				attendanceRepository.save(studentClass2);
			}
		}
		return "Sucess";
	}

}
