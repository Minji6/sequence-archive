package com.sequence.demo.controller;

import com.sequence.demo.entity.Project;
import com.sequence.demo.repository.ProjectRepository;
import com.sequence.demo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable int projectId, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(projectId, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable int projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}/evaluation-status")
    public ResponseEntity<Boolean> isEvaluationComplete(@PathVariable int projectId) {
        boolean evaluationComplete = projectService.isEvaluationComplete(projectId);
        return ResponseEntity.ok(evaluationComplete);
    }

    @PutMapping("/{projectId}/status")
    public ResponseEntity<Project> updateProjectStatus(@PathVariable int projectId, @RequestParam String status) {
        Project updatedProject = projectService.updateProjectStatus(projectId, status);
        return ResponseEntity.ok(updatedProject);
    }
}
