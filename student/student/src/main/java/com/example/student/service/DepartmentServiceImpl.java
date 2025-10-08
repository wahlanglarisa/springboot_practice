package com.example.student.service;

import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.student.model.Department;
import com.example.student.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public List<Department> findAllDepartments() {
		// TODO Auto-generated method stub
		Sort sort=Sort.by(Sort.Direction.ASC,"id");

		return departmentRepository.findAll(sort);
	}

}
