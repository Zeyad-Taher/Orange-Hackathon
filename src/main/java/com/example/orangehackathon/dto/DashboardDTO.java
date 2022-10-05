package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Supplier;
import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DashboardDTO {
    private int maxNumberOfCourses;
    private int numberOfCurrentCourses;
    private int totalNumberOfStudents;
    private float totalDebt;
    private float totalPaid;
    private float totalRemaining;
    private ArrayList<Supplier> suppliers;

    public DashboardDTO(){
        setMaxNumberOfCourses(40);
    }
}


