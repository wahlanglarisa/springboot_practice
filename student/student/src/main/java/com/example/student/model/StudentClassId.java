package com.example.student.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class StudentClassId implements Serializable {
    private Long student;
    private Long class_Course;
    private Date date;

    // Default constructor
    public StudentClassId() {}

    // Constructor
    public StudentClassId(Long student, Long class_Course, Date date) {
        this.student = student;
        this.class_Course = class_Course;
        this.date = date;
    }

    // equals and hashCode are required
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentClassId)) return false;
        StudentClassId that = (StudentClassId) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(class_Course, that.class_Course) &&
               Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, class_Course, date);
    }

    // Getters and Setters (optional if using public fields or Lombok)
}
