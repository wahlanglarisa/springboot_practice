package com.example.student.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="course",uniqueConstraints = {@UniqueConstraint(columnNames = {"course_name"})})
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String courseName;
	private Long credit;
	@ManyToMany(mappedBy = "courses",cascade = CascadeType.REMOVE)
	List<Student> student;
	@OneToMany(mappedBy = "course_class")
//	@JoinColumn(name="class_course_id",referencedColumnName = "id")
	private List<Class_Course> course_class=new ArrayList<Class_Course>();
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
	private List<Test> tests=new ArrayList<Test>();
	@ManyToOne
	@JoinColumn(name="dept_id")
	private Department department;
	@ManyToMany(mappedBy = "courses")
	private List<Professor> professors;
	@Column(name="semester",nullable = true)
	private Long semester;
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	@Column(name="isElective",nullable = true)
	private Boolean elective;
	public Course(String courseName, long i) {
		super();
		
		this.courseName = courseName;
		this.credit = i;
	}
}
