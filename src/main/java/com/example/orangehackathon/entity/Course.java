package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.CourseDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
    @Column(name="location",nullable = false)
    private String location;
    @Column(name="passed",columnDefinition = "boolean default false")
    private boolean passed;
    @Column(name="startDate",nullable = false)
    private Date startDate;
    @Column(name="endDate",nullable = false)
    private Date endDate;
    @Column(name="startTime",nullable = false)
    private Time startTime;
    @Column(name="endTime",nullable = false)
    private Time endTime;
    @OneToMany
    @Column(name="prerequisites",nullable = false)
    private List<Course> prerequisites;
    @ManyToMany
    List<Skill> skills;
    @ManyToMany
    List<Student> students;
    @JoinColumn(name = "supplier_id")
    @ManyToOne
    private Supplier supplier;

    public Course(CourseDTO courseDTO){
        this.id=courseDTO.getId();
        this.name=courseDTO.getName();
        this.category=courseDTO.getCategory();
        this.location=courseDTO.getLocation();
        this.startDate=courseDTO.getStartDate();
        this.endDate=courseDTO.getEndDate();
        this.startTime=courseDTO.getStartTime();
        this.endTime=courseDTO.getEndTime();
        this.prerequisites=new ArrayList<>();
        this.skills=new ArrayList<>();
        this.students=new ArrayList<>();
    }
}
