package com.faruk.demo.repository;

import javax.transaction.Transactional;

import com.faruk.demo.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CourseReporsitory extends JpaRepository<Course, Long>{
    public void deleteById(Long id);
}
