package com.example.student.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="class")
public class Class_Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Time time;
	
	private long semester;
	@ManyToOne
	@JoinColumn(name="class_course_id",referencedColumnName = "id")

	private Course course_class;
	@OneToMany(mappedBy = "class_Course")
	private List<StudentClass> students=new ArrayList<StudentClass>();
	private String day;
	@ManyToOne
	@JoinColumn(name="prof_id")
	private Professor professor;
	@OneToMany(mappedBy = "class_Course")
	private List<Attendance> attendances=new ArrayList<Attendance>();
}
 