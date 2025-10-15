package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.model.Branch;
import com.example.student.model.Class_Course;
import com.example.student.model.Course;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class_Course, Long>{
    @Query("select distinct class from Class_Course class "+
    "join class.branch branch"+
    " where branch.id=:id"+
    " and class.semester=:semester"+
    " and class.id not in "+
    "(select cc.id from StudentClass"+
    " st_class join st_class.class_Course cc "+
    "join st_class.student st where st.ID=:st_id)")
    public List<Class_Course>  findByBranchIDAndSemester(@Param("id") long id,@Param("semester") Long semester,@Param("st_id") Long st_id);
}
