package com.example.orangehackathon.dto;

import lombok.*;

import java.sql.Time;
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
    private String cooperation;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}
