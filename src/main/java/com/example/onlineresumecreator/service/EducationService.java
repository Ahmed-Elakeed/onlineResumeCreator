package com.example.onlineresumecreator.service;

import com.example.onlineresumecreator.model.Education;
import com.example.onlineresumecreator.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EducationService implements ServiceInterface<Education> {
    private EducationRepository educationRepository;

    private UserService userService;

    @Autowired
    public EducationService(EducationRepository educationRepository, UserService userService) {
        this.educationRepository = educationRepository;
        this.userService = userService;
    }

    @Override
    public List<Education> getAll() {
        return this.educationRepository.findAll();
    }

    @Override
    public Education getById(Long id) {
        return this.educationRepository.findById(id).orElse(null);
    }

    @Override
    public Education save(Education education) {
        education.setUser(this.userService.getCurrentUser());
        return this.educationRepository.save(education);
    }

    @Override
    public Education update(Long id, Education education) {
        education.setUser(this.userService.getCurrentUser());
        education.setEducationId(id);
        return this.educationRepository.save(education);
    }

    public void deleteEducation(Long id) {
        this.educationRepository.deleteById(id);
    }
}
