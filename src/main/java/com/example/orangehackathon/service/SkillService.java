package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.SkillDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public void addSkill(SkillDTO skillDTO) {
        Skill skill=new Skill(skillDTO);
        skillRepository.save(skill);
    }

    public ArrayList<Skill> showAllSkills() {
        return (ArrayList<Skill>) skillRepository.findAll();
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public Skill findSkillById(Long skillId){
        return skillRepository.findById(skillId).get();
    }

    public void saveSkill(Skill skill){
        skillRepository.save(skill);
    }
}
