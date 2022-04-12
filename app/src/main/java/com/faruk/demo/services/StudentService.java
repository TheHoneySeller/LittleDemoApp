package com.faruk.demo.services;

import java.util.List;
import java.util.Optional;

import com.faruk.demo.model.Course;
import com.faruk.demo.model.Student;
import com.faruk.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseService courseService;

    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getEnrolledStudents(Long courseId) {
        return studentRepository.findByCourses_Id(courseId);
    }

    public List<Student> getNotEnrolledStudents(Long courseId) {
        return studentRepository.findByCourses_IdIsNot(courseId);
    }

    public void enrollStudent(Long studentId, Long courseId) {
        Course course = courseService.getCourse(courseId);
        Student student = studentRepository.getById(studentId);

        if ( course == null || student == null || !student.getLevel().equals(course.getLevel()) ) {
            return;
        }
       
        student.enrollInCourse(course);
        studentRepository.save(student); 
        //no need to call course.enrollStudent() it is already called by student.enrollInCourse()
    }

    public void cancelEnrollment(Long studentId, Long courseId) {
        Course course = courseService.getCourse(courseId);
        Student student = studentRepository.getById(studentId);

        student.cancelEnrollment(course);
        studentRepository.save(student);
    }

    public void cancelAllEnrollments(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (!optionalStudent.isEmpty()) {
            Student student = optionalStudent.get();
            //student.getCourses().forEach( course -> course.removeStudent(student)); //doesn't work because of concurrency reasons
            List<Course> enrolledCourses = student.getCourses();
            while (enrolledCourses.size() > 0) {
                enrolledCourses.get(0).removeStudent(student);
            }
        }
        
    }
    
}
