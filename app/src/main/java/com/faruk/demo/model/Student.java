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
public class Student {


    public Student() {
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(Long id, String name, String surname, Level level) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.level = level;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Course> getCourses() {
        return courses;
    }

    



    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Level level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private final List<Course> courses = new ArrayList<Course>();;

    public void enrollInCourse(Course course) {
        if(!courses.contains(course)){ //this if clause prevents infinite recursion
            courses.add(course);
            course.enrollStudent(this);
        }
    }

    public void cancelEnrollment(Course course) {
        if(courses.contains(course)) {
            courses.remove(course);
            course.removeStudent(this);
        }
    }
    
}
