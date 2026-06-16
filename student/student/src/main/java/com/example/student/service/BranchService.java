package com.example.student.service;

import java.util.List;

import com.example.student.model.Branch;
import com.example.student.model.Department;

public interface BranchService {
	public List<Branch> findBranchList();
	public List<Branch> findBranchByDepartment(Department department);
}
