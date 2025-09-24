package com.example.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Branch;
import com.example.student.model.Department;
import com.example.student.repository.BranchRepository;
@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	private BranchRepository branchRepository;
	@Override
	public List<Branch> findBranchList() {
		// TODO Auto-generated method stub
		return branchRepository.findAll();
	}
	@Override
	public List<Branch> findBranchByDepartment(Department department) {
		// TODO Auto-generated method stub
		return branchRepository.findByDepartment(department);
	}

}
