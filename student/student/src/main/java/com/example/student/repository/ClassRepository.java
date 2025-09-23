package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.student.model.Branch;
import com.example.student.model.Class_Course;
import com.example.student.model.Course;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class_Course, Long>{
    @Query("select class from Class_Course class join class.branch branch where branch.id=:id and class.semester=:semester")
    public List<Class_Course>  findByBranchIDAndSemester(long id,Long semester);
}
