package com.example.student.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "test_student")
// @IdClass(TestStudentID.class)

public class TestStudent {
	@Column(name="marks",updatable = true)
	private Long marks;
	@ManyToOne
	@JoinColumn(name = "test_id")
	
	private Test test;
	@ManyToOne
	@JoinColumn(name = "st_id")

//    @OnDelete(action = OnDeleteAction.CASCADE)

	private Student student;  
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	    @Column(name = "test_studentid")

	private String testStudentID;

} 
