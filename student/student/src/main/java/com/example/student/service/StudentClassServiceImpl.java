package com.example.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.StudentClass;
import com.example.student.model.wrapper.AddStudentClass;
import com.example.student.model.wrapper.SaveStudentClass;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.repository.StudentClassRepository;
import com.example.student.repository.StudentRepository;

@Service
public class StudentClassServiceImpl implements StudentClassService{
    @Autowired
    private StudentClassRepository studentClassRepository; 
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentClass savStudentClass(SaveStudentClass saveStudentClass) {
        // TODO Auto-generated method stub
        StudentClass studentClass=new StudentClass();
        List<AddStudentClass> addStudentClasses=saveStudentClass.getAddStudentClasses();
        for(AddStudentClass addStudentClass:addStudentClasses){
            if(addStudentClass.isChecked()){
                        StudentClass studentClass2=new StudentClass();

                studentClass2.setClass_Course(addStudentClass.getClass_Course());
                studentClass2.setStudent(addStudentClass.getStudent());
                studentClassRepository.save(studentClass2);
                studentClass=studentClass2;
            }
        }
        return studentClass;
    }
  

}
