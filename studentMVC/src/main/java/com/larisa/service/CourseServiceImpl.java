package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.larisa.dao.CourseDao;
import com.larisa.dto.Course;
import com.larisa.dto.Student;
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDao.getCourses();
	}
	@Override
	public Page<Course> getCoursesByStudent(Student student,Pageable pageable) {
		// TODO Auto-generated method stub
		return courseDao.getCoursesByStudent(student,pageable);
	}
	@Override
	public List<Course> getCoursesNotTakenByStudent(Student student) {
		// TODO Auto-generated method stub
		return courseDao.getCoursesNotTakenByStudent(student);
	}

}
