package com.example.orangehackathon.controller;

import com.example.orangehackathon.dto.SkillDTO;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addSkill(@RequestBody SkillDTO skillDTO){
        return skillService.addSkill(skillDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> showAllSkills(){
        return skillService.showAllSkills();
    }
}
