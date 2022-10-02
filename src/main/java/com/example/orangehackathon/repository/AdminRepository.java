package com.example.orangehackathon.repository;

import com.example.orangehackathon.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,String> {
    Admin findByUsername(String username);
    boolean existsByUsername(String username);
}