package com.faruk.demo.controller;

import java.util.List;

import com.faruk.demo.model.Course;
import com.faruk.demo.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    
    
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course")
    public Course postCourse(@RequestBody Course course) {
        return courseService.addNewCourse(course); 
    }

    @GetMapping("/course")
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @DeleteMapping("/course")
    public void deleteCourse(@RequestParam Long courseId){
        courseService.cancelAllEnrollments(courseId);
        courseService.deleteCourse(courseId);
    }

    
}
