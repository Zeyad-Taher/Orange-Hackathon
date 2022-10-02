package com.example.orangehackathon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AdminDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}