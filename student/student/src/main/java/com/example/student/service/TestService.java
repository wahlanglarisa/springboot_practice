package com.example.student.service;

import java.util.List;

import com.example.student.model.Professor;
import com.example.student.model.Test;

public interface TestService {
public  Test createtest(Test test);
public List<Test> findByProfessor(Professor professor);
}
