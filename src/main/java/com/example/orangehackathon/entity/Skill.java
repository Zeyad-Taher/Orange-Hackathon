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
@Entity(name="skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @JsonIgnore
    @ManyToMany
    Set<Course> courseSkills;
    @JsonIgnore
    @ManyToMany(mappedBy = "stackSkills")
    Set<Stack> stacks;

    public Skill(Long id,String name){
        this.id=id;
        this.name=name;
        this.courseSkills=new HashSet<>();
        this.stacks=new HashSet<>();
    }
}
