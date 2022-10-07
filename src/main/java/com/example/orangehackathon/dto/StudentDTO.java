package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String gender;
    private String phoneNumber;
    private String nationalId;
    List<Course> courses;
    List<Skill> gainedSkills;
}
