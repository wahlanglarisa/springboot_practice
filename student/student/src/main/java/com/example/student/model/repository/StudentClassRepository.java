package com.example.student.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.StudentClass;
import com.example.student.model.StudentClassId;

public interface StudentClassRepository extends JpaRepository<StudentClass, StudentClassId> {

}
