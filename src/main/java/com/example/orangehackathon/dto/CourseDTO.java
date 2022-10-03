package com.example.orangehackathon.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CourseDTO {
    private Long id;
    private String name;
    private String category;
    private int duration;
}
