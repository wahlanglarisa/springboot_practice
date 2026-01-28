package com.example.student.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Attendance;
import com.example.student.model.AttendanceID;
import com.example.student.model.Student;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceID>{
    public long countByStudentAndDate(Student student,Date date);
}
