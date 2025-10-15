package com.example.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Branch;
import com.example.student.model.Class_Course;
import com.example.student.repository.ClassRepository;
@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassRepository classRepository;
	
	@Override
	public Class_Course savClass_Course(Class_Course class_Course) {
		// TODO Auto-generated method stub
		return classRepository.save(class_Course);
	}

	@Override
	public Class_Course findById(long id) {
		// TODO Auto-generated method stub
		return classRepository.getReferenceById(id);
	}

	@Override
	public void deleteClassById(long id) {
		// TODO Auto-generated method stub
		classRepository.deleteById(id);;
		
	}

	@Override
	public List<Class_Course> findByBranchIDAndSemester(Long id, Long semester,Long st_id) {
		// TODO Auto-generated method stub
		return classRepository.findByBranchIDAndSemester(id, semester,st_id);
	}

	

}
