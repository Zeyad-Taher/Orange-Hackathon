package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.dto.SupplierDTO;
import com.example.orangehackathon.entity.Supplier;
import com.example.orangehackathon.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "/add")
    public void addSupplier(@RequestBody SupplierDTO supplierDTO){
        supplierService.addSupplier(supplierDTO);
    }

    @GetMapping(value = "/all")
    public ArrayList<Supplier> showAllSuppliers(){
        return supplierService.showAllSuppliers();
    }

    @DeleteMapping(value = "/del/{id}")
    public void deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
    }
}
