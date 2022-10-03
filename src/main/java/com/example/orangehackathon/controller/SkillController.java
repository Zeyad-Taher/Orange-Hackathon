package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.SkillDTO;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping(value = "/add")
    public void addSkill(@RequestBody SkillDTO skillDTO){
        skillService.addSkill(skillDTO);
    }

    @GetMapping(value = "/all")
    public ArrayList<Skill> showAllSkills(){
        return skillService.showAllSkills();
    }

    @DeleteMapping(value = "/del/{id}")
    public void deleteCourse(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}
