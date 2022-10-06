package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.JobDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "organization",nullable = false)
    private String organization;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "salary",nullable = false)
    private float salary;
    @ManyToMany
    private List<Skill> requiredSkills;
    public Job(JobDTO jobDTO){
        this.id=jobDTO.getId();
        this.organization=jobDTO.getOrganization();
        this.description=jobDTO.getDescription();
        this.salary=jobDTO.getSalary();
        this.requiredSkills=new ArrayList<>();
    }
}
