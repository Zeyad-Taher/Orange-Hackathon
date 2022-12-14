package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.StudentDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name="firstname",nullable = false)
    private String firstname;
    @Column(name="lastname",nullable = false)
    private String lastname;
    @Email
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="age",nullable = false)
    private int age;
    @Column(name="gender",nullable = false)
    private String gender;
    @Column(name="phoneNumber",nullable = false)
    private String phoneNumber;
    @Column(name="nationalId",nullable = false)
    private String nationalId;
    @ManyToMany(mappedBy = "students")
    List<Course> courses;
    @ManyToMany
    List<Skill> gainedSkills;

    public Student(StudentDTO studentDTO){
        this.id=studentDTO.getId();
        this.firstname=studentDTO.getFirstname();
        this.lastname=studentDTO.getLastname();
        this.email=studentDTO.getEmail();
        this.age= studentDTO.getAge();
        this.gender=studentDTO.getGender();
        this.phoneNumber=studentDTO.getPhoneNumber();
        this.nationalId=studentDTO.getNationalId();
        this.courses=new ArrayList<>();
        this.gainedSkills=new ArrayList<>();
    }
}
