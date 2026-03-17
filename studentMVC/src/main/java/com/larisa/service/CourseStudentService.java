package com.larisa.service;

import java.util.List;

public interface CourseStudentService {
	public String saveCourseStudent(Long stID,List<Long> courseID);
	public String deleteCourseStudent(Long stID,Long courseID) ;

}
