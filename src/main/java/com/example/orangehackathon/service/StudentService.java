package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.dto.StudentDTO;
import com.example.orangehackathon.entity.Student;
import com.example.orangehackathon.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(StudentDTO studentDTO) {
        Student student=new Student(studentDTO);
        studentRepository.save(student);
    }

    public ArrayList<Student> showAllStudents() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void setNumberOfStudents(DashboardDTO dashboardDTO) {
        int students=showAllStudents().size();
        dashboardDTO.setTotalNumberOfStudents(students);
    }

    public ArrayList<Student> findStudentWithSorting(String field){
        return (ArrayList<Student>) studentRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Student> findStudentWithPagination(int offset, int pageSize){
        Page<Student> students = studentRepository.findAll(PageRequest.of(offset,pageSize));
        return students;
    }

    public Page<Student> findStudentWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Student> students = studentRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return students;
    }
}
