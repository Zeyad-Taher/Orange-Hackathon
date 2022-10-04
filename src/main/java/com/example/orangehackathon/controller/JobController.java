package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.JobDTO;
import com.example.orangehackathon.entity.Job;
import com.example.orangehackathon.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping(value = "/add")
    public void addJob(@RequestBody JobDTO jobDTO){
        jobService.addJob(jobDTO);
    }

    @GetMapping(value = "/all")
    public ArrayList<Job> showAllJobs(){
        return jobService.showAllJobs();
    }

    @DeleteMapping(value = "/del/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }
}
