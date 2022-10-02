package com.example.orangehackathon.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SkillDTO {
    private Long id;
    private String name;
}
