package com.example.student.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
	public Long getCredit() {
		return credit;
	}
	public void setCredit(Long credit) {
		this.credit = credit;
	}
	public List<Class_Course> getCourse_class() {
		return course_class;
	}
	public void setCourse_class(List<Class_Course> course_class) {
		this.course_class = course_class;
	}
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
