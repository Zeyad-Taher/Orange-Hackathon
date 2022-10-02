package com.example.orangehackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="username",nullable = false,unique = true)
    private String username;
    @Column(name="firstname",nullable = false)
    private String firstname;
    @Column(name="lastname",nullable = false)
    private String lastname;
    @Email
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @JsonIgnore
    @ManyToMany
    Set<Course> enrolledCourses;
}
