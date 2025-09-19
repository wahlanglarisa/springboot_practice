package com.example.student.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Branch {
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String branchName;
		@ManyToOne
		@JoinColumn(name="dept_id")
		private Department department;
		@OneToMany(mappedBy = "branch")
		private List<Student> students=new ArrayList<Student>();

		
		
}