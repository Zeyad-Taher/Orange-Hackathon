package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.entity.Student;
import com.example.orangehackathon.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private StudentService studentService;

    public void addCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO);
        courseRepository.save(course);
    }

    public ArrayList<Course> showAllCourses() {
        return (ArrayList<Course>) courseRepository.findAll();
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void addSkillToCourse(Long courseId, Long skillId) {
        Course course = courseRepository.findById(courseId).get();
        Skill skill = skillService.findSkillById(skillId);
        List<Course> courses=skill.getCourses();
        List<Skill> skills=course.getSkills();
        courses.add(course);
        skills.add(skill);
        skill.setCourses(courses);
        course.setSkills(skills);
        skillService.saveSkill(skill);
        courseRepository.save(course);
    }

    public void enrollStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).get();
        Student student = studentService.findStudentById(studentId);
        List<Course> courses=student.getCourses();
        List<Student> students=course.getStudents();
        courses.add(course);
        students.add(student);
        student.setCourses(courses);
        course.setStudents(students);
        studentService.saveStudent(student);
        courseRepository.save(course);
    }

    public void addPrerequisiteToCourse(Long courseId, Long preId) {
        Course course=courseRepository.findById(courseId).get();
        Course prerequisite=courseRepository.findById(preId).get();
        List<Course> prerequisites=course.getPrerequisites();
        prerequisites.add(prerequisite);
        course.setPrerequisites(prerequisites);
        courseRepository.save(course);
    }
}
