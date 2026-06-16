package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.student.model.ClassTime;
import com.example.student.model.wrapper.GetAvailClassTime;

public interface ClassTimeRepository extends JpaRepository<ClassTime, Long> {
    @Query("select  new com.example.student.model.wrapper.GetAvailClassTime(ct.id,ct.startTime,ct.endTime) from "+
    "ClassTime ct where ct.id "+
    "not in (select distinct ct.id from Class_Course cc join cc.classTime ct join cc.branch br where cc.day=:day and br.id=:br_id and cc.semester=:semester)")
    public List<GetAvailClassTime> getAvailableClassTime(@Param("day") String day,@Param("br_id") long br_id,@Param("semester") long semester);

}
