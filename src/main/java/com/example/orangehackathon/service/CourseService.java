package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.repository.CourseRepository;
import com.example.orangehackathon.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SkillRepository skillRepository;

    public void addCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO.getId(),courseDTO.getName(), courseDTO.getCategory());
        courseRepository.save(course);
    }

    public ArrayList<Course> showAllCourses() {
        return (ArrayList<Course>) courseRepository.findAll();
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void assignSkillToCourse(Long courseId, Long skillId) {
        Course course = courseRepository.findById(courseId).get();
        Skill skill = skillRepository.findById(skillId).get();
        Set<Course> courses=skill.getCourseSkills();
        Set<Skill> skills=course.getAchievableSkills();
        courses.add(course);
        skills.add(skill);
        skill.setCourseSkills(courses);
        course.setAchievableSkills(skills);
        skillRepository.save(skill);
        courseRepository.save(course);
    }
}
