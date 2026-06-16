package com.example.student.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
public class StudentClassId implements Serializable {
    private Long student;
    private Long class_Course;
   

    // Default constructor
    public StudentClassId() {}

    // Constructor
    public StudentClassId(Long student, Long class_Course, Date date) {
        this.student = student;
        this.class_Course = class_Course;
    }

    // equals and hashCode are required
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentClassId)) return false;
        StudentClassId that = (StudentClassId) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(class_Course, that.class_Course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, class_Course);
    }

    // Getters and Setters (optional if using public fields or Lombok)
}
