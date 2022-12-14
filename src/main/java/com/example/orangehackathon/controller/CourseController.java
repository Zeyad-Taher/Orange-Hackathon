package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/course/add")
    public ResponseEntity<?> addCourse(@RequestBody CourseDTO courseDTO){
        return courseService.addCourse(courseDTO);
    }

    @GetMapping(value = "/course/all")
    public ResponseEntity<?> showAllCourses(){
        return courseService.showAllCourses();
    }

    @PutMapping(value="/course/add/skill/{courseId}/{skillId}")
    public ResponseEntity<?> addSkillToCourse(@PathVariable Long courseId,@PathVariable Long skillId){
        return courseService.addSkillToCourse(courseId,skillId);
    }

    @PutMapping(value="/course/enroll/student/{courseId}/{studentId}")
    public ResponseEntity<?> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) throws ParseException {
        return courseService.enrollStudentToCourse(courseId,studentId);
    }

    @PutMapping(value="/course/add/pre/{courseId}/{preId}")
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Long courseId, @PathVariable Long preId){
        return courseService.addPrerequisiteToCourse(courseId,preId);
    }

    @PutMapping(value="/course/add/sup/{courseId}/{supId}")
    public ResponseEntity<?> assignSupToCourse(@PathVariable Long courseId, @PathVariable Long supId){
        return courseService.addSupplierToCourse(courseId,supId);
    }

    @PutMapping(value="/course/del/skill/{courseId}/{skillId}")
    public ResponseEntity<?> delSkillToCourse(@PathVariable Long courseId, @PathVariable Long skillId){
        return courseService.delSkillToCourse(courseId,skillId);
    }

    @PutMapping(value="/course/del/pre/{courseId}/{preId}")
    public ResponseEntity<?> delStudentToCourse(@PathVariable Long courseId, @PathVariable Long preId){
        return courseService.delPrerequisiteToCourse(courseId,preId);
    }

    @PutMapping(value="/course/del/sup/{courseId}")
    public ResponseEntity<?> delSupToCourse(@PathVariable Long courseId){
        return courseService.delSupplierToCourse(courseId);
    }

    @PutMapping(value="/course/update/student/{courseId}/{studentId}/{progress}")
    public ResponseEntity<?> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId,@PathVariable String progress) {
        return courseService.setStudentProgressInCourse(courseId,studentId,progress);
    }

    @GetMapping(value = "/course/recommend/{courseId}")
    public ResponseEntity<?> recommendStudentToCourse(@PathVariable Long courseId){
        return courseService.recommendStudentsToCourse(courseId);
    }

    @GetMapping(value = "/job/recommend/{jobId}")
    public ResponseEntity<?> recommendStudentToJob(@PathVariable Long jobId){
        return courseService.recommendStudentsToJob(jobId);
    }

    @PutMapping(value="/course/done/{courseId}/{studentId}")
    public ResponseEntity<?> finishCourseByStudent(@PathVariable Long courseId,@PathVariable Long studentId){
        return courseService.finishCourseByStudent(courseId,studentId);
    }
}
