package com.example.orangehackathon.repository;

import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public ArrayList<Student> findAllByFirstnameStartsWith(String name);
    public ArrayList<Student> findAllByCoursesContains(Course course);
    public ArrayList<Student> findAllByGenderIgnoreCase(String gender);
}
