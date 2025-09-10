package com.example.student.service;

import com.example.student.model.Class_Course;

public interface ClassService {
	public Class_Course savClass_Course(Class_Course class_Course);
	public Class_Course findById(long id);
	public void deleteClassById(long id);
}
