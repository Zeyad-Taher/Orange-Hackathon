package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Supplier;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@ToString

public class DashboardDTO {
    private int maxNumberOfCourses;
    private int numberOfCurrentCourses;
    private int totalNumberOfStudents;
    private float totalDebt;
    private float totalPaid;
    private float totalRemaining;
    private List<Supplier> suppliers;

    public DashboardDTO(){
        setMaxNumberOfCourses(40);
    }
}


