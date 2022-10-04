package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Supplier;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CourseDTO {
    private Long id;
    private String name;
    private String category;
    private String location;
    private String cooperation;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private Supplier supplier;
}
