package com.faruk.demo.services;

import java.util.List;

import com.faruk.demo.beans.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CourseService {

    private final RestTemplate restTemplate;

    @Autowired
    public CourseService(RestTemplateBuilder builder) {
        this.restTemplate = builder.rootUri("http://app:8081").build();
    }


    public Course addNewCourse(Course course) {
        return restTemplate.postForObject("/course", course, Course.class);
    }


    public List<Course> getAllCourser() {
        return restTemplate.exchange("/course", HttpMethod.GET, null, new ParameterizedTypeReference<List<Course>>() {})
            .getBody();
    }

    public void deleteCourse(Long courseId) {
        restTemplate.delete("/course?courseId={courseId}", courseId);
    }
    
}
