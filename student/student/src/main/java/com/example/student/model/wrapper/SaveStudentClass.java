package com.example.student.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentClass {
    private List<AddStudentClass> addStudentClasses=new ArrayList<AddStudentClass>();
}
