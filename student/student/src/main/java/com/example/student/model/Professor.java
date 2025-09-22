package com.example.student.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "professor")
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	private String firstName;
	private String lastName;
	private String email;
	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "course_professor", joinColumns = { @JoinColumn(name = "prof_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private List<Course> courses = new ArrayList<Course>();
	@OneToOne(mappedBy = "professor")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Department department;

	@OneToMany(mappedBy = "professor")
	@OnDelete(action = OnDeleteAction.SET_NULL)

	private List<Class_Course> class_Course = new ArrayList<Class_Course>();

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department departments;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)

	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "professor")
	List<Test> tests = new ArrayList<Test>();

	public Professor(String firstName, String lastName, String email, List<Class_Course> class_Courses, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.class_Course = class_Courses;
		this.user = user;
	}

}
