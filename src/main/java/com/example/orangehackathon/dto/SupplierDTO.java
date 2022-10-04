package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Course;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplierDTO {
    private Long id;
    private String name;
    private float debt;
    private float paid;
}
