package com.example.student.model.wrapper;

import java.util.List;

import com.example.student.model.TestStudent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTestStudent {
    private List<TestStudent> testStudents;
}
