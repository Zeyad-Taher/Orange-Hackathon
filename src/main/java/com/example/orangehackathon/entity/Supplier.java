package com.example.orangehackathon.entity;

import com.example.orangehackathon.dto.SupplierDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "debt",nullable = false)
    private float debt;
    @Column(name = "paid",nullable = false)
    private float paid;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private List<Course> suppliedCourses;
    public Supplier(SupplierDTO supplierDTO){
        this.id=supplierDTO.getId();
        this.name=supplierDTO.getName();
        this.debt=supplierDTO.getDebt();
        this.paid= supplierDTO.getPaid();
        this.suppliedCourses=new ArrayList<>();
    }
}
