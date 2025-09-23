package com.example.student.model.wrapper;

import java.util.List;

import com.example.student.model.Class_Course;
import com.example.student.model.Student;
import com.example.student.model.StudentClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentClass {
    private Student student;
    private Class_Course class_Course;
    private boolean checked;
}
