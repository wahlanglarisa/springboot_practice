package com.example.student.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Class_Course;
@Repository
public interface ClassRepository extends JpaRepository<Class_Course, Long>{

}
