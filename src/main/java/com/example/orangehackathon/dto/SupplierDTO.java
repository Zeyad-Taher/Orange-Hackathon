package com.example.orangehackathon.dto;

import lombok.*;

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
