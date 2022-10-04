package com.example.orangehackathon.dto;

import com.example.orangehackathon.entity.Skill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDTO {
    private Long id;
    private String organization;
    private String description;
    private float salary;
}
