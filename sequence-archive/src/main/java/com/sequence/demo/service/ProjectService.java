package com.sequence.demo.service;

import com.sequence.demo.entity.Project;
import com.sequence.demo.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public boolean isEvaluationComplete(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
        return project.getTeamEvaluations().stream().allMatch(evaluation -> evaluation.isCompleted());
    }

    public Project updateProjectStatus(int projectId, String status) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
        project.setStatus(status);
        return projectRepository.save(project);
    }
}
