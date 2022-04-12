package com.faruk.demo.services;

import java.util.List;
import java.util.Optional;

import com.faruk.demo.model.Course;
import com.faruk.demo.model.Student;
import com.faruk.demo.repository.CourseReporsitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseReporsitory courseReporsitory;

    public Course addNewCourse(Course course) {
        return courseReporsitory.save(course);
    }

    public List<Course> getAllCourses() {
        return courseReporsitory.findAll();
    }

    public void deleteCourse(Long id) {
        courseReporsitory.deleteById(id);
    }

    public Course getCourse(Long courseId) {
        Optional<Course> optionalCourse = courseReporsitory.findById(courseId);
        if (optionalCourse.isEmpty()) {
            return null;
        } else {
            return optionalCourse.get();
        }
    }

    public void cancelAllEnrollments(Long courseId) {
        Optional<Course> optionalCourse = courseReporsitory.findById(courseId);
        if(!optionalCourse.isEmpty()) {
            Course course = optionalCourse.get();
            List<Student> enrolledStudents = course.getEnrolledStudents();
            //course.getEnrolledStudents().forEach(student -> student.cancelEnrollment(course)); //doesn't work because of concurrency reasons
            while (enrolledStudents.size() > 0 ) {
                enrolledStudents.get(0).cancelEnrollment(course);
            }
            
            
        }

    }

    


}
