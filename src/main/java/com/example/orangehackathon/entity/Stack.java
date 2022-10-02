package com.example.orangehackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="stack")
public class Stack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="category",nullable = false)
    private String category;
    @JsonIgnore
    @ManyToMany
    Set<Skill> stackSkills;
    @JsonIgnore
    @ManyToMany
    Set<Student> studentStacks;
}
