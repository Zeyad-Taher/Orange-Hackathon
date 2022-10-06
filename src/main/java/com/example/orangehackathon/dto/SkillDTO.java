package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Course;
import lombok.*;

import java.util.ArrayList;
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
