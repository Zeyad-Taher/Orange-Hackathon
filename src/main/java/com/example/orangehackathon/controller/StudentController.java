package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.StudentDTO;
import com.example.orangehackathon.entity.Student;
import com.example.orangehackathon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/add")
    public void addStudent(@RequestBody StudentDTO studentDTO){
        studentService.addStudent(studentDTO);
    }

    @GetMapping(value = "/all")
    public ArrayList<Student> showAllStudents(){
        return studentService.showAllStudents();
    }

    @DeleteMapping(value = "/del/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping("/{field}")
    private ArrayList<Student> getStudentsWithSort(@PathVariable String field){
        return studentService.findStudentWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private Page<Student> getStudentsWithPagination(@PathVariable int offset,@PathVariable int pageSize){
        return studentService.findStudentWithPagination(offset,pageSize);
    }

    @GetMapping("/pagination/{offset}/{pageSize/{field}")
    private Page<Student> getStudentsWithPaginationAndSort(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        return studentService.findStudentWithPaginationAndSorting(offset,pageSize,field);
    }
}
