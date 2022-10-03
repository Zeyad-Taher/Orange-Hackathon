package com.example.orangehackathon.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="category",nullable = false)
    private String category;
    @Column(name="passed",columnDefinition = "boolean default false")
    private boolean passed;
    @ManyToMany(mappedBy = "courseSkills")
    List<Skill> achievableSkills;
    @ManyToMany(mappedBy = "coursePrerequisites")
    List<Prerequisite> prerequisites;
    @ManyToMany(mappedBy = "enrolledCourses")
    List<Student> studentsCourses;

    public Course(Long id,String name,String category){
        this.id=id;
        this.name=name;
        this.category=category;
        this.achievableSkills=new ArrayList<>();
        this.prerequisites=new ArrayList<>();
        this.studentsCourses=new ArrayList<>();
    }
}
