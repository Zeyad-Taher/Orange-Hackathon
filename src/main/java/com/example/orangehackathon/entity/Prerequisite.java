package com.example.orangehackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="prerequisite")
public class Prerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="category",nullable = false)
    private String category;
    @JsonIgnore
    @ManyToMany(mappedBy = "prerequisites")
    List<Course> courses;
}
