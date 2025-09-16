package com.example.student.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@IdClass(TestStudentID.class)

public class TestStudent {
	@Id
	private long marks;
	@ManyToOne
	@JoinColumn(name = "test_id")
	@Id
	private Test test;
	@ManyToOne
	@JoinColumn(name = "st_id")
	@Id
//    @OnDelete(action = OnDeleteAction.CASCADE)

	private Student student;  
}
