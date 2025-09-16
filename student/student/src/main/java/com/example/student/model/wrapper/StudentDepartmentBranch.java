package com.example.student.model.wrapper;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDepartmentBranch {
	public String stName;
	public String deptName;
	public String branchName;
}
