package com.faruk.demo.beans;

import java.util.ArrayList;
import java.util.List;

import com.faruk.demo.beans.Enums.Level;

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

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    private Long id;

    private String title;
    private Level level;

    private List<Student> enrolledStudents = new ArrayList<Student>();

}
