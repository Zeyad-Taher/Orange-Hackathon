package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.AdminDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="admin")
public class Admin {
    @Id
    @Column(name="username",nullable = false,unique = true)
    private String username;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="firstname",nullable = false)
    private String firstname;
    @Column(name="lastname",nullable = false)
    private String lastname;
    @Email
    @Column(name="email",nullable = false,unique = true)
    private String email;

    public Admin(AdminDTO adminDTO) {
        this.username= adminDTO.getUsername();
        this.password= adminDTO.getPassword();
        this.firstname= adminDTO.getFirstname();
        this.lastname= adminDTO.getLastname();
        this.email= adminDTO.getEmail();
    }
}
