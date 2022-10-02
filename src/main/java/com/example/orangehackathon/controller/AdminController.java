package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.AdminDTO;
import com.example.orangehackathon.dto.AdminLoginDTO;
import com.example.orangehackathon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/auth/signup")
    public ResponseEntity<?> createNewUser(@RequestBody AdminDTO adminDTO) {
        return adminService.createNewUser(adminDTO);
    }

    @PostMapping(value = "/auth/login")
    public void login(@RequestBody AdminLoginDTO adminLoginDTO) {}
}
