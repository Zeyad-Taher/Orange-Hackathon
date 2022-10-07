package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.JobDTO;
import com.example.orangehackathon.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addJob(@RequestBody JobDTO jobDTO){
        return jobService.addJob(jobDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> showAllJobs(){
        return jobService.showAllJobs();
    }

    @PutMapping(value="/add/skill/{jobId}/{skillId}")
    public ResponseEntity<?> addSkillToJob(@PathVariable Long jobId,@PathVariable Long skillId){
        return jobService.addSkillToJob(jobId,skillId);
    }
}
