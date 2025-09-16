package com.example.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Professor;
import com.example.student.model.Student;
import com.example.student.model.Test;
import com.example.student.model.TestStudent;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.TestRepository;
import com.example.student.repository.TestStudentRepository;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestRepository testRepository;
	@Autowired 
	private StudentRepository studentRepository;
	@Autowired
	private TestStudentRepository testStudentRepository;
	
	@Override
	public Test createtest(Test test) {
		// TODO Auto-generated method stub
		List<Student> students=studentRepository.findStudentByClassID(test.getClass_Course().getId());
		List<TestStudent> testStudents=new ArrayList<TestStudent>();
		Test savedTest=testRepository.save(test);
		for(Student st:students) {
			TestStudent testStudent=new TestStudent();

			testStudent.setStudent(st);
			testStudent.setTest(savedTest);
			testStudents.add(testStudent);
		}
		testStudentRepository.saveAll(testStudents);
		return savedTest;
	}

	@Override
	public List<Test> findByProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return testRepository.findByProfessor(professor);
	}

}
