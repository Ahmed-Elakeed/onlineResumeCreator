package com.example.onlineresumecreator.service;

import com.example.onlineresumecreator.model.Experience;
import com.example.onlineresumecreator.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService implements ServiceInterface<Experience> {

    private ExperienceRepository experienceRepository;

    private UserService userService;

    @Autowired
    public ExperienceService(ExperienceRepository experienceRepository, UserService userService) {
        this.experienceRepository = experienceRepository;
        this.userService = userService;
    }

    @Override
    public List<Experience> getAll() {
        return this.experienceRepository.findAll();
    }

    @Override
    public Experience getById(Long id) {
        return this.experienceRepository.findById(id).orElse(null);
    }

    @Override
    public Experience save(Experience experience) {
        experience.setUser(this.userService.getCurrentUser());
        return this.experienceRepository.save(experience);
    }

    @Override
    public Experience update(Long id, Experience experience) {
        experience.setUser(this.userService.getCurrentUser());
        experience.setExperienceId(id);
        return this.experienceRepository.save(experience);
    }

    public void deleteExperience(Long id) {
        this.experienceRepository.deleteById(id);
    }

}
