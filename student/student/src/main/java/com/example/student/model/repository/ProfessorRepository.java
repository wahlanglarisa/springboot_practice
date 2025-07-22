package com.example.student.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Professor;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
