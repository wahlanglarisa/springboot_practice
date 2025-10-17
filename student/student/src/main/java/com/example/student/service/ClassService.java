package com.example.student.service;

import java.util.List;

import com.example.student.model.Branch;
import com.example.student.model.Class_Course;
import com.example.student.model.Course;

public interface ClassService {
	public Class_Course savClass_Course(Class_Course class_Course);
	public Class_Course findById(long id);
	public void deleteClassById(long id);
	public List<Class_Course> findByBranchIDAndSemester(Long id,Long semester,Long st_id);
	    public List<Class_Course> findByCourseAndSemesterAndBranch(Course course,Long Semester,Branch Branch);
}
