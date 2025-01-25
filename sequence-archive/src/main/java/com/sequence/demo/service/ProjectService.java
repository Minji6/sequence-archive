package com.sequence.demo.service;

import com.sequence.demo.entity.Project;
import com.sequence.demo.repository.ProjectRepository;

import java.util.List;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(int projectId, Project updatedProject) {
        Project exisitingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));

        exisitingProject.setTitle(updatedProject.getTitle());
        exisitingProject.setDescription(updatedProject.getDescription());
        exisitingProject.setSkills(updatedProject.getSkills());
        exisitingProject.setImages(updatedProject.getImages());
        exisitingProject.setLinks(updatedProject.getLinks());

        return projectRepository.save(exisitingProject);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
    }
}
