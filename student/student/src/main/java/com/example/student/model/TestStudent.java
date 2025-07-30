package com.example.student.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "test_student")
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
	private Student student;  
}
