package com.example.student.service;

import java.util.List;

import com.example.student.model.StudentClass;
import com.example.student.model.wrapper.SaveStudentClass;
import com.example.student.model.wrapper.StudentRoutine;

public interface StudentClassService {
    public StudentClass savStudentClass(SaveStudentClass saveStudentClass);

}
