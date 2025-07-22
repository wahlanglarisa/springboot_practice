package com.example.student.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="course",uniqueConstraints = {@UniqueConstraint(columnNames = {"course_name"})})
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String courseName;
	private Long credit;
	@ManyToMany(mappedBy = "courses")
	List<Student> student;
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	public List<Student> getProfessor() {
		return professor;
	}
	public void setProfessor(List<Student> professor) {
		this.professor = professor;
	}
	@ManyToMany(mappedBy = "courses")
	List<Student> professor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Long getCredits() {
		return credit;
	}
	public void setCredits(Long credits) {
		this.credit = credits;
	}
	public Course(String courseName, long i) {
		super();
		
		this.courseName = courseName;
		this.credit = i;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
}
