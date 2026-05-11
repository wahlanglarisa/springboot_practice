package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.larisa.dao.CourseStudentDao;
import com.larisa.dao.StudentAddressDao;
import com.larisa.dao.StudentDao;
import com.larisa.dao.StudentQualificationDao;
import com.larisa.dao.UserDao;
import com.larisa.dto.GetAllStudentData;
import com.larisa.dto.Student;
import com.larisa.dto.User;
import com.larisa.dto.UserStudent;

@Service
public class StudentServiceImpl implements StudentService {
	@Override
	public long getCountofStudents() {
		// TODO Auto-generated method stub
		return studentDao.getCountofStudents();
	}

	@Override
	public GetAllStudentData getAllStudentData(long id) {
		// TODO Auto-generated method stub
		return studentDao.getAllStudentData(id);
	}

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CourseStudentDao courseStudentDao;
	@Autowired
	private StudentAddressDao studentAddressDao;

	@Autowired
	private StudentQualificationDao studentQualificationDao;

	@Override
	public Page<Student> getListOfStudents(Pageable pageable) {

		// TODO Auto-generated method stub
		return studentDao.getListOfStudents(pageable);
	}

	@Override
	public void addStudent(UserStudent st) {
		System.out.println(st.getPassword() + "\t" + st.getEmail());
		User user = userDao.saveUser(new User(st.getPassword(), st.getEmail()),"Student");
		System.out.println(user.getUserid());
		Student student = new Student(st.getPhone_no(), st.getEmail(), st.getLast_name(), st.getFirst_name(),
				user.getUserid());
		student.setUser_id(user.getUserid());
		student.setProfile_picture(st.getProfile_picture());
		studentDao.addStudent(student);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent(UserStudent st) {
		Student student = new Student(st.getPhone_no(), st.getEmail(), st.getLast_name(), st.getFirst_name(),
				st.getUser_id());
		student.setId(st.getSt_id());
		System.out.println("Update student " + st.getProfile_picture());
		student.setProfile_picture(st.getProfile_picture());
		studentDao.updateStudent(student);
		User user = new User(st.getPassword(), st.getEmail());
		user.setUserid(st.getUser_id());
		if (st.getStudentQualifications().size() > 0)
			studentQualificationDao.addQualifications(st.getStudentQualifications());

		userDao.updateUser(user);
		studentAddressDao.updateAddress(st);
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStudent(Student st) {
		courseStudentDao.deleteCourseStudentByStID(st.getId());
		studentAddressDao.deleteByStID(st.getId());
		studentQualificationDao.deleteStudentQualificationsByStID(st.getId());
		studentDao.deleteStudent(st);
		userDao.deleteUser(st.getUser_id());
		// TODO Auto-generated method stub

	}

	@Override
	public Student getStudent(long id) {
		// TODO Auto-generated method stub
		return studentDao.getStudent(id);
	}

	@Override
	public Student findStudentByEmail(String email) {
		// TODO Auto-generated method stub
		return studentDao.findStudentByEmail(email);
	}

	@Override
	public Student findStudentByPhoneNo(Long phone_no) {
		// TODO Auto-generated method stub
		return studentDao.findStudentByPhoneNo(phone_no);
	}

	@Override
	public long getCountCourses(long stID) {
		// TODO Auto-generated method stub
		return studentDao.getCountCourses(stID);
	}

}
