package com.example.student.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="professor")
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	private String firstName;
	private String lastName;
	private String email;
	@ManyToMany
	@JoinTable(name="course_professor",joinColumns = {@JoinColumn(name="prof_id")},inverseJoinColumns =  {@JoinColumn(name="course_id")})
	private List<Course> courses=new ArrayList<Course>();
	@OneToOne(mappedBy = "professor")
	private Department department;
	
	@OneToMany(mappedBy = "professor")
	private List<Class_Course> class_Course=new ArrayList<Class_Course>();
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department departments; 
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Department getDepartments() {
		return departments;
	}
	public void setDepartments(Department departments) {
		this.departments = departments;
	}
	public List<Class_Course> getClass_Course() {
		return class_Course;
	}
	public void setClass_Course(List<Class_Course> class_Course) {
		this.class_Course = class_Course;
	}
	public List<Course> getCourses() { 
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Professor( String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD; 
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
