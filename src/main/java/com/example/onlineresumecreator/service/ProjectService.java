package com.example.onlineresumecreator.service;

import com.example.onlineresumecreator.model.Project;
import com.example.onlineresumecreator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ServiceInterface<Project> {
    private ProjectRepository projectRepository;
    private UserService userService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public List<Project> getAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) {
        return this.projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project save(Project project) {
        project.setUser(this.userService.getCurrentUser());
        return this.projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        project.setProjectId(id);
        project.setUser(this.userService.getCurrentUser());
        return this.save(project);
    }

    public void deleteProject(Long id) {
        this.projectRepository.deleteById(id);
    }

}
