package com.larisa.service;

import java.util.List;

import com.larisa.dto.GetStudentClass;

public interface ClassCourseService {
	public List<GetStudentClass> getStudentClasses(Long stID);

}
