package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.CourseDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="category",nullable = false)
    private String category;
    @Column(name="duration",nullable = false)
    private int duration;
    @Column(name="passed",columnDefinition = "boolean default false")
    private boolean passed;
    @OneToMany
    @Column(name="prerequisites",nullable = false)
    private List<Course> prerequisites;
    @ManyToMany
    List<Skill> skills;
    @ManyToMany
    List<Student> students;

    public Course(CourseDTO courseDTO){
        this.id=courseDTO.getId();
        this.name=courseDTO.getName();
        this.category=courseDTO.getCategory();
        this.duration=courseDTO.getDuration();
        this.prerequisites=new ArrayList<>();
        this.skills=new ArrayList<>();
        this.students=new ArrayList<>();
    }
}
