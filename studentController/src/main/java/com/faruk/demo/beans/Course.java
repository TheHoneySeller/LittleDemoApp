package com.faruk.demo.beans;


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


    private Long id;

    private String title;
    private Level level;
}
