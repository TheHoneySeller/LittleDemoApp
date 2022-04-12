package com.faruk.demo.controller;

import java.time.Duration;
import java.util.List;

import com.faruk.demo.beans.Course;
import com.faruk.demo.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {
    
    @Autowired
    CourseService courseService;

    private final Bucket bucket;

    //constants that define the cost of api calls. 
    //Some api calls need to be low cost because they might be utilized frequently by the frontend ui
    //must be greater than zero
    //private final long LOW_RATE = 1l; no api call in this controller that uses a low rate. 
    private final long HIGH_RATE = 2l;

    //bucket capacity which will fill in in DURATION minutes and 
    //will be spent depending on rates defined above by the api calls
    private final long BUCKET_CAPACITY = 40;
    private final long FILL_DURATION = 1; //in minutes 

    public CourseController(){
        Bandwidth limit = Bandwidth.classic(BUCKET_CAPACITY, Refill.greedy(BUCKET_CAPACITY, Duration.ofMinutes(FILL_DURATION)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();
    }

    @PostMapping("/course")
    public ResponseEntity<Course> addNewCourse(@RequestBody Course course) {
        if(bucket.tryConsume(HIGH_RATE)) {
            return ResponseEntity.ok(courseService.addNewCourse(course));
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getAllCourses() {
        if(bucket.tryConsume(HIGH_RATE)) {
            return ResponseEntity.ok(courseService.getAllCourser());
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }

    @DeleteMapping("/course")
    public ResponseEntity<Object> deleteCourse(@RequestParam Long courseId){
        if(bucket.tryConsume(HIGH_RATE)) {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }

}
