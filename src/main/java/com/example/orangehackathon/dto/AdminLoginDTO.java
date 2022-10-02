package com.example.orangehackathon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AdminLoginDTO {
    private String username;
    private String password;
}
