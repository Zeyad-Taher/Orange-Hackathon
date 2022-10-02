package com.example.orangehackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    Set<Skill> achievableSkills;
    @ManyToMany(mappedBy = "coursePrerequisites")
    Set<Prerequisite> prerequisites;
    @ManyToMany(mappedBy = "enrolledCourses")
    Set<Student> studentsCourses;

    public Course(Long id,String name,String category){
        this.id=id;
        this.name=name;
        this.category=category;
        this.achievableSkills=new HashSet<>();
        this.prerequisites=new HashSet<>();
        this.studentsCourses=new HashSet<>();
    }
}
