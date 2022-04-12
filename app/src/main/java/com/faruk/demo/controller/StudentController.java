package com.faruk.demo.controller;

import java.util.List;

import com.faruk.demo.model.Student;
import com.faruk.demo.services.StudentService;

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
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @DeleteMapping("/student")
    public void deleteStudent(@RequestParam Long studentId){
        studentService.cancelAllEnrollments(studentId);
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/enrolledStudents")
    public List<Student> getEnrolledStudents(@RequestParam Long courseId) {
        return studentService.getEnrolledStudents(courseId);
    }

    @GetMapping("/notEnrolledStudents")
    public List<Student> getNotEnrolledStudents(@RequestParam Long courseId) {
        return studentService.getNotEnrolledStudents(courseId);
    }

    @PostMapping("/enrollStudent")
    public void enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId){
        studentService.enrollStudent(studentId, courseId);
    }

    @DeleteMapping("/cancelEnrollment")
    public void cancelEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentService.cancelEnrollment(studentId, courseId);
    }

    @GetMapping("/enrolledInCourse")
    public List<Student> getStudentsEnrolledInCourse(@RequestParam Long courseId) {
        return studentService.getEnrolledStudents(courseId);
    }

    @GetMapping("/notEnrolledInCourse")
    public List<Student> getStudentsNotEnrolledInCourse(@RequestParam Long courseId) {
        return studentService.getNotEnrolledStudents(courseId);
    }
}
