package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.dto.StudentDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.entity.Student;
import com.example.orangehackathon.repository.CourseRepository;
import com.example.orangehackathon.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<?> addStudent(StudentDTO studentDTO) {
        Student student=new Student(studentDTO);
        studentRepository.save(student);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showAllStudents() {
        ArrayList<Student> students = (ArrayList<Student>) studentRepository.findAll();
        return new ResponseEntity<>(students,HttpStatus.ACCEPTED);
    }

    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void setNumberOfStudents(DashboardDTO dashboardDTO) {
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        dashboardDTO.setTotalNumberOfStudents(students.size());
    }

    public ResponseEntity<?> findStudentWithSorting(String field){
        ArrayList<Student> students = (ArrayList<Student>) studentRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        return new ResponseEntity<>(students,HttpStatus.ACCEPTED);
    }

    public Page<Student> findStudentWithPagination(int offset, int pageSize){
        Page<Student> students = studentRepository.findAll(PageRequest.of(offset,pageSize));
        return students;
    }

    public Page<Student> findStudentWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Student> students = studentRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return students;
    }

    public ResponseEntity<?> addSkillToStudent(Long studentId, Long skillId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student==null){
            return new ResponseEntity<>("Please enter a valid student ID", HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillService.findSkillById(skillId);
        if(skill==null){
            return new ResponseEntity<>("Please enter a valid skill ID", HttpStatus.BAD_REQUEST);
        }
        ArrayList<Skill> skills= (ArrayList<Skill>) student.getGainedSkills();
        skills.add(skill);
        student.setGainedSkills(skills);
        studentRepository.save(student);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> delSkillToStudent(Long studentId, Long skillId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student==null){
            return new ResponseEntity<>("Please enter a valid student ID", HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillService.findSkillById(skillId);
        if(skill==null){
            return new ResponseEntity<>("Please enter a valid skill ID", HttpStatus.BAD_REQUEST);
        }
        ArrayList<Skill> skills= (ArrayList<Skill>) student.getGainedSkills();
        skills.remove(skill);
        student.setGainedSkills(skills);
        studentRepository.save(student);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showStudentsWithNameStarting(String name) {
        ArrayList<Student> students = studentRepository.findAllByFirstnameStartsWith(name);
        return new ResponseEntity<>(students,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showStudentsEnrollingCourse(Long courseId) {
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        ArrayList<Student> students=studentRepository.findAllByCoursesContains(course);
        return new ResponseEntity<>(students,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showStudentsByGender(String gender) {
        ArrayList<Student> students=studentRepository.findAllByGenderIgnoreCase(gender);
        return new ResponseEntity<>(students,HttpStatus.ACCEPTED);
    }
}
