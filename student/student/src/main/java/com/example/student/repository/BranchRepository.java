package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.Branch;
import com.example.student.model.Department;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    public List<Branch> findByDepartment(Department department);
}
