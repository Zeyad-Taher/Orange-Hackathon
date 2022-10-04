package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.JobDTO;
import com.example.orangehackathon.entity.Job;
import com.example.orangehackathon.repository.JobRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public void addJob(JobDTO jobDTO) {
        Job job=new Job(jobDTO);
        jobRepository.save(job);
    }

    public ArrayList<Job> showAllJobs() {
        return (ArrayList<Job>) jobRepository.findAll();
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public Job findJobById(Long jobId) {
        return jobRepository.findById(jobId).get();
    }

    public void saveJob(Job job) {
        jobRepository.save(job);
    }
}
