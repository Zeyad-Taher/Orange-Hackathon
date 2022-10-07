package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.JobDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Job;
import com.example.orangehackathon.entity.Skill;
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
    @Autowired
    private SkillService skillService;

    public ResponseEntity<?> addJob(JobDTO jobDTO) {
        Job job=new Job(jobDTO);
        jobRepository.save(job);
        return new ResponseEntity<>(job, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showAllJobs() {
        List<Job> jobs = (List<Job>) jobRepository.findAll();
        return new ResponseEntity<>(jobs,HttpStatus.ACCEPTED);
    }

    public Job findJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    public ResponseEntity<?> addSkillToJob(Long jobId, Long skillId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        if(job == null){
            return new ResponseEntity<>("Invalid job ID",HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillService.findSkillById(skillId);
        if(skill == null){
            return new ResponseEntity<>("Invalid skill ID",HttpStatus.BAD_REQUEST);
        }
        List<Skill> skills= job.getRequiredSkills();
        skills.add(skill);
        job.setRequiredSkills(skills);
        jobRepository.save(job);
        return new ResponseEntity<>(job,HttpStatus.ACCEPTED);
    }
}
