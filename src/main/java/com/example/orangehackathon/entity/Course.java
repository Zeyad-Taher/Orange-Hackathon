package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.CourseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="code",nullable = false)
    private String code;
    @Column(name="category",nullable = false)
    private String category;
    @Column(name="location",nullable = false)
    private String location;
    @Column(name="progress",columnDefinition = "varchar(255) default 'Not invited'")
    private String progress;
    @Column(name="startDate",nullable = false)
    private String startDate;
    @Column(name="endDate",nullable = false)
    private String endDate;
    @Column(name="startTime",nullable = false)
    private String startTime;
    @Column(name="endTime",nullable = false)
    private String endTime;
    @JoinColumn(name = "supplier_id")
    @ManyToOne
    private Supplier supplier;
    @ManyToMany
    private List<Skill> skills;
    @OneToMany
    @Column(name="prerequisites",nullable = false)
    private List<Course> prerequisites;
    @JsonIgnore
    @ManyToMany
    private List<Student> students;

    public Course(CourseDTO courseDTO){
        this.id=courseDTO.getId();
        this.name=courseDTO.getName();
        this.code=courseDTO.getCode();
        this.category=courseDTO.getCategory();
        this.location=courseDTO.getLocation();
        this.progress=courseDTO.getProgress();
        this.startDate=courseDTO.getStartDate();
        this.endDate=courseDTO.getEndDate();
        this.startTime=courseDTO.getStartTime();
        this.endTime=courseDTO.getEndTime();
        this.prerequisites=new ArrayList<>();
        this.skills=new ArrayList<>();
        this.students=new ArrayList<>();
        this.supplier=new Supplier();
    }
}
