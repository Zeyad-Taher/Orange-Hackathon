package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.service.CourseService;
import com.example.orangehackathon.service.StudentService;
import com.example.orangehackathon.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/dashboard")
    public DashboardDTO getDashboard(){
        DashboardDTO dashboardDTO = new DashboardDTO();
        studentService.setNumberOfStudents(dashboardDTO);
        courseService.setNumberOfCourses(dashboardDTO);
        return supplierService.getDashboard(dashboardDTO);
    }
}
