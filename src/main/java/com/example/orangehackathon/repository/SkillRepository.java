package com.example.orangehackathon.repository;

import com.example.orangehackathon.entity.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill,Long> {

}