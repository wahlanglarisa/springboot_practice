package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
