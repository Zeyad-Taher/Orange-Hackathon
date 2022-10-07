package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.SkillDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public ResponseEntity<?> addSkill(SkillDTO skillDTO) {
        Skill skill=new Skill(skillDTO);
        skillRepository.save(skill);
        return new ResponseEntity<>(skill, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showAllSkills() {
        ArrayList<Skill> skills = (ArrayList<Skill>) skillRepository.findAll();
        return new ResponseEntity<>(skills,HttpStatus.ACCEPTED);
    }

    public Skill findSkillById(Long skillId){
        return skillRepository.findById(skillId).orElse(null);
    }
}
