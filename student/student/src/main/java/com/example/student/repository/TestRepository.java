package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Professor;
import com.example.student.model.Test;
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	public List<Test> findByProfessor(Professor professor);
}
