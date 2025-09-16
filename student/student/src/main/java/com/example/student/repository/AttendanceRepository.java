package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.Attendance;
import com.example.student.model.AttendanceID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceID>{

}
