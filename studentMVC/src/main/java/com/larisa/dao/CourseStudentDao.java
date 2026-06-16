package com.larisa.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

public interface CourseStudentDao {
public String saveCourseStudent(Long stID,List<Long> courseID) throws DuplicateKeyException;
public String deleteCourseStudent(Long stID,Long courseID) ;
public String deleteCourseStudentByStID(Long stID) ;


}
