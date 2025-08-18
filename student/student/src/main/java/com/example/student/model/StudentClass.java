package com.example.student.model;

import java.sql.Date;
import java.sql.Time;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(StudentClassId.class)
@Table(name = "student_class")
public class StudentClass {
	@Id
	@ManyToOne()
	@JoinColumn(name="st_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)

	private Student student;
	@ManyToOne
	@Id
	@JoinColumn(name = "class_id")
	private Class_Course class_Course;
	
}  
