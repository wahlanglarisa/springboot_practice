package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.ClassCourseDao;
import com.larisa.dto.GetStudentClass;

@Service
public class ClassCourseServiceImpl implements ClassCourseService{
	@Autowired
	private ClassCourseDao classCourseDao;
	@Override
	public List<GetStudentClass> getStudentClasses(Long stID) {
		// TODO Auto-generated method stub
		return classCourseDao.getStudentClasses(stID);
	}

}
