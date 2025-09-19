package com.example.student.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dName;
	@OneToOne()
	@JoinColumn(name="hod_id")
	   @OnDelete(action = OnDeleteAction.SET_NULL)

	private Professor professor;
	@OneToMany(mappedBy = "departments")
	private List<Professor> professors=new ArrayList<Professor>();
	@OneToMany(mappedBy = "department")
	private List<Branch> branches=new ArrayList<Branch>();
	@OneToMany(mappedBy = "department")
	private List<Course> courses=new ArrayList<Course>();
}
