package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/add")
    public void addCourse(@RequestBody CourseDTO courseDTO){
        courseService.addCourse(courseDTO);
    }

    @GetMapping(value = "/all")
    public ArrayList<Course> showAllCourses(){
        return courseService.showAllCourses();
    }

    @DeleteMapping(value = "/del/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }

    @PutMapping(value="/add/skill/{courseId}/{skillId}")
    public void addSkillToCourse(@PathVariable Long courseId, @PathVariable Long skillId){
        courseService.addSkillToCourse(courseId,skillId);
    }

    @PutMapping(value="/enroll/student/{courseId}/{studentId}")
    public void enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        courseService.enrollStudentToCourse(courseId,studentId);
    }

    @PutMapping(value="/add/pre/{courseId}/{preId}")
    public void assignStudentToCourse(@PathVariable Long courseId, @PathVariable Long preId){
        courseService.addPrerequisiteToCourse(courseId,preId);
    }

    @PutMapping(value="/add/sup/{courseId}/{supId}")
    public void assignSupToCourse(@PathVariable Long courseId, @PathVariable Long supId){
        courseService.addSupplierToCourse(courseId,supId);
    }
}
