package com.faruk.demo.Services;

import java.util.List;

import com.faruk.demo.beans.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {
    
    private final RestTemplate restTemplate;

    @Autowired
    public StudentService(RestTemplateBuilder builder) {
        this.restTemplate = builder.rootUri("http://app:8081").build();
    }
    
    
    public Student postStudent(Student student) {
        return restTemplate.postForObject("/student", student, Student.class);
    }

    
    
    public List<Student> getStudents() {
        return restTemplate.exchange("/student", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {})
            .getBody();
    }

    
    public void deleteStudent(Long studentId) {
        restTemplate.delete("/student?studentId={studentId}", studentId);
    }


    public void enrollStudent(Long studentId, Long courseId) {
        restTemplate.exchange("/enrollStudent?studentId={studentId}&courseId={courseId}", HttpMethod.POST, null, Student.class, studentId, courseId);
    }

    public void cancelEnrollment(Long studentId, Long courseId) {
        restTemplate.exchange("/cancelEnrollment?studentId={studentId}&courseId={courseId}", HttpMethod.DELETE, null, new ParameterizedTypeReference<List<Student>>() {}, studentId, courseId);
    }

    public List<Student> getEnrolledStudents(Long courseId) {
        List<Student> result = restTemplate.exchange("/enrolledInCourse?courseId={courseId}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {}, courseId)
            .getBody();
        return result;
    }

    public List<Student> getNotEnrolledStudents(Long courseId) {
        return restTemplate.exchange("/notEnrolledInCourse?courseId={courseId}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {}, courseId)
        .getBody();
    }
}
