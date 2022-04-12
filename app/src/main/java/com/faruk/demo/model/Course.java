package com.faruk.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.faruk.demo.model.Enums.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
    public Course(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }



    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Level level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private final List<Student> enrolledStudents = new ArrayList<Student>();

    public void enrollStudent(Student student) {
        if(!enrolledStudents.contains(student)){ //this if clause prevents infinite recursion
            enrolledStudents.add(student);
            student.enrollInCourse(this);
        }
    }

    public void removeStudent(Student student) {
        if(enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            student.cancelEnrollment(this);
        }
    }


}

