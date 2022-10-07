package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.StudentDTO;
import com.example.orangehackathon.entity.Student;
import com.example.orangehackathon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO){
        return studentService.addStudent(studentDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> showAllStudents(){
        return studentService.showAllStudents();
    }

    @GetMapping("/{field}")
    private ResponseEntity<?> getStudentsWithSort(@PathVariable String field){
        return studentService.findStudentWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private Page<Student> getStudentsWithPagination(@PathVariable int offset,@PathVariable int pageSize){
        return studentService.findStudentWithPagination(offset,pageSize);
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    private Page<Student> getStudentsWithPaginationAndSort(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        return studentService.findStudentWithPaginationAndSorting(offset,pageSize,field);
    }

    @PutMapping(value="/add/skill/{studentId}/{skillId}")
    public ResponseEntity<?> addSkillToStudent(@PathVariable Long studentId, @PathVariable Long skillId){
        return studentService.addSkillToStudent(studentId,skillId);
    }

    @PutMapping(value="/del/skill/{studentId}/{skillId}")
    public ResponseEntity<?> delSkillToStudent(@PathVariable Long studentId, @PathVariable Long skillId){
        return studentService.delSkillToStudent(studentId,skillId);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> showStudentsWithNameStarting(@PathVariable String name){
        return studentService.showStudentsWithNameStarting(name);
    }
    @GetMapping(value = "/{courseId}")
    public ResponseEntity<?> showStudentsEnrollingCourse(@PathVariable Long courseId){
        return studentService.showStudentsEnrollingCourse(courseId);
    }
    @GetMapping(value = "/{gender}")
    public ResponseEntity<?> showStudentsByGender(@PathVariable String gender){
        return studentService.showStudentsByGender(gender);
    }
}
