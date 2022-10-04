package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.SkillDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "skills")
    List<Course> courses;
    @JsonIgnore
    @ManyToMany(mappedBy = "gainedSkills")
    List<Student> students;

    @JsonIgnore
    @ManyToMany(mappedBy = "requiredSkills")
    List<Job> jobs;

    public Skill(SkillDTO skillDTO){
        this.id=skillDTO.getId();
        this.name=skillDTO.getName();
        this.courses=new ArrayList<>();
        this.students=new ArrayList<>();
        this.jobs=new ArrayList<>();
    }
}
