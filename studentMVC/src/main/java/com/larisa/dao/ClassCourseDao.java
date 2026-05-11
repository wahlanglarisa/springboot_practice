package com.larisa.dao;

import java.util.List;

import com.larisa.dto.GetStudentClass;

public interface ClassCourseDao {
	public List<GetStudentClass> getStudentClasses(Long stID);
}
