package com.faruk.demo.beans;


import com.faruk.demo.beans.Enums.Level;

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
    
    
    private Long id;
    private String name;
    private String surname;
    private Level level;

    
    
}
