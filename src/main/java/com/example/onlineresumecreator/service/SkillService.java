package com.example.onlineresumecreator.service;


import com.example.onlineresumecreator.model.Skill;
import com.example.onlineresumecreator.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService implements ServiceInterface<Skill> {
    private SkillRepository skillRepository;

    private UserService userService;

    @Autowired
    public SkillService(SkillRepository skillRepository, UserService userService) {
        this.skillRepository = skillRepository;
        this.userService = userService;
    }

    @Override
    public List<Skill> getAll() {
        return this.skillRepository.findAll();
    }

    @Override
    public Skill getById(Long id) {
        return this.skillRepository.findById(id).orElse(null);
    }

    @Override
    public Skill save(Skill skill) {
        skill.setUser(this.userService.getCurrentUser());
        return this.skillRepository.save(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {
        skill.setSkillId(id);
        skill.setUser(this.userService.getCurrentUser());
        return this.skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        this.skillRepository.deleteById(id);
    }
}
