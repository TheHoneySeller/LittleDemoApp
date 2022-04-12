package com.faruk.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.faruk.demo.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
    public void deleteById(Long id);
    public List<Student> findByCourses_Id(Long courseId);
    public List<Student> findByCourses_IdIsNot(Long couseId);
}