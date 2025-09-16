package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.TestStudent;
import com.example.student.model.TestStudentID;

public interface TestStudentRepository extends JpaRepository<TestStudent,TestStudentID> {
	
}
