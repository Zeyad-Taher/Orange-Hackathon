package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.JobDTO;
import com.example.orangehackathon.entity.Job;
import com.example.orangehackathon.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public ResponseEntity<?> addJob(JobDTO jobDTO) {
        Job job=new Job(jobDTO);
        jobRepository.save(job);
        return new ResponseEntity<>(job, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showAllJobs() {
        List<Job> jobs = (List<Job>) jobRepository.findAll();
        return new ResponseEntity<>(jobs,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteJob(Long id) {
        if(!jobRepository.findById(id).isPresent()){
            return new ResponseEntity<>("Invalid job ID",HttpStatus.BAD_REQUEST);
        }
        jobRepository.deleteById(id);
        return new ResponseEntity<>("Job deleted successfully",HttpStatus.ACCEPTED);
    }

    public Job findJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }
}
