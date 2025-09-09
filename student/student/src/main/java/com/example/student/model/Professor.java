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
	@OneToOne(orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Professor(String firstName, String lastName, String email,List<Class_Course> class_Courses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.class_Course=class_Courses;
	}
	
	
}
 