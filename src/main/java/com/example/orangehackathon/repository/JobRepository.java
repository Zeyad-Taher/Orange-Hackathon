package com.example.orangehackathon.repository;

import com.example.orangehackathon.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job,Long> {
}
