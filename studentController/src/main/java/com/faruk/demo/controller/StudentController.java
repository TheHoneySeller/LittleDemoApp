package com.faruk.demo.controller;

import java.time.Duration;
import java.util.List;

import com.faruk.demo.Services.StudentService;
import com.faruk.demo.beans.Student;

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
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    private final Bucket bucket;

    //constants that define the cost of api calls. 
    //Some api calls need to be low cost because they might be utilized frequently by the frontend ui
    //must be greater than zero
    private final long LOW_RATE = 1l; 
    private final long HIGH_RATE = 2l;

    //bucket capacity which will fill in in DURATION minutes and 
    //will be spent depending on rates defined above by the api calls
    private final long BUCKET_CAPACITY = 40;
    private final long FILL_DURATION = 1; //in minutes 

    public StudentController() {
        Bandwidth limit = Bandwidth.classic(BUCKET_CAPACITY, Refill.greedy(BUCKET_CAPACITY, Duration.ofMinutes(FILL_DURATION)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();
    }

    @PostMapping("/student")
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        if(bucket.tryConsume(HIGH_RATE)) {
            return ResponseEntity.ok(studentService.postStudent(student));
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        
    }

    
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudents() {
        if(bucket.tryConsume(HIGH_RATE)) {
            return ResponseEntity.ok(studentService.getStudents());
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        
    }

    @DeleteMapping("/student")
    public ResponseEntity<Object> deleteStudent(@RequestParam Long studentId) {
        if(bucket.tryConsume(HIGH_RATE)) {
            studentService.deleteStudent(studentId);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        
    }

    @PostMapping("/enrollStudent") 
    public ResponseEntity<Object> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        if(bucket.tryConsume(HIGH_RATE)) {
            studentService.enrollStudent(studentId, courseId);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        
    }

    @DeleteMapping("/cancelEnrollment")
    public ResponseEntity<Object> cancelEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        if(bucket.tryConsume(HIGH_RATE)) {
            studentService.cancelEnrollment(studentId, courseId);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        
    }

    @GetMapping("/enrolledInCourse")
    public ResponseEntity<List<Student>> getStudentsEnrolledInCourse(@RequestParam Long courseId) {
        if(bucket.tryConsume(LOW_RATE)) {
            return ResponseEntity.ok(studentService.getEnrolledStudents(courseId));
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        
    }

    @GetMapping("/notEnrolledInCourse")
    public ResponseEntity<List<Student>> getStudentsNotEnrolledInCourse(@RequestParam Long courseId) {
        if(bucket.tryConsume(LOW_RATE)) {
            return ResponseEntity.ok(studentService.getNotEnrolledStudents(courseId));
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

    }


}
