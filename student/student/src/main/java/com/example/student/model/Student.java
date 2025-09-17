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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(columnNames = { "emailid" }) })
public class Student {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	private String firstName;
	private String lastName;
	private String emailID;
	@ManyToMany()
	@JoinTable(name = "course_student", joinColumns = { @JoinColumn(name = "st_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private List<Course> courses = new ArrayList<Course>();

	private Long semester;
	@OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<StudentClass> class_Courses = new ArrayList<StudentClass>();
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<TestStudent> testStudents = new ArrayList<TestStudent>();
	@OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<Attendance> attendances = new ArrayList<Attendance>();
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch; 

	public Student(String firstName, String lastName, String emailID, Long semester, User user,Branch branch) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.semester = semester;
		this.user = user;
		this.branch=branch;
	}

}
