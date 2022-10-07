package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.dto.SupplierDTO;
import com.example.orangehackathon.entity.Supplier;
import com.example.orangehackathon.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO supplierDTO){
        return supplierService.addSupplier(supplierDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> showAllSuppliers(){
        return supplierService.showAllSuppliers();
    }
}
