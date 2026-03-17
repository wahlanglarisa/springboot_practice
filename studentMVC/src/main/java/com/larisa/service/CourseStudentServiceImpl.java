package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.CourseStudentDao;
@Service
public class CourseStudentServiceImpl implements CourseStudentService {
	@Override
	public String deleteCourseStudent(Long stID, Long courseID) {
		// TODO Auto-generated method stub
		return courseStudentDao.deleteCourseStudent(stID, courseID);
	}
	@Autowired
	private CourseStudentDao courseStudentDao;
	@Override
	public String saveCourseStudent(Long stID, List<Long> courseID) {
		// TODO Auto-generated method stub
		return courseStudentDao.saveCourseStudent(stID, courseID);
	}

}
