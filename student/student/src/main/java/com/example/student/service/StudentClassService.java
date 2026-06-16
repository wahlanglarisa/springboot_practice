package com.example.student.service;

import java.sql.Date;
import java.util.List;

import com.example.student.model.Student;
import com.example.student.model.StudentClass;
import com.example.student.model.wrapper.SaveStudentClass;
import com.example.student.model.wrapper.StudentRoutine;

public interface StudentClassService {
    public StudentClass savStudentClass(SaveStudentClass saveStudentClass);

}
