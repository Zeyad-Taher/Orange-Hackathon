package com.example.orangehackathon.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CourseDTO {
    private Long id;
    private String name;
    private String code;
    private String category;
    private String location;
    private String progress;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}
