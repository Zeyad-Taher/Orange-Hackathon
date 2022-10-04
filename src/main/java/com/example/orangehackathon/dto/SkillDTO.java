package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Job;
import com.example.orangehackathon.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SkillDTO {
    private Long id;
    private String name;
    List<Course> courses;
}
