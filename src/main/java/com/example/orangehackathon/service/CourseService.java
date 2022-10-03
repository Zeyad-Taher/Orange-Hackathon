package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.repository.CourseRepository;
import com.example.orangehackathon.repository.SkillRepository;
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
        Skill skill = skillService.findSkillById(skillId);
        List<Course> courses=skill.getCourseSkills();
        List<Skill> skills=course.getAchievableSkills();
        courses.add(course);
        skills.add(skill);
        skill.setCourseSkills(courses);
        course.setAchievableSkills(skills);
        skillService.saveSkill(skill);
        courseRepository.save(course);
    }
}
