package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String status;

    @Column
    private String thumbnailUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<ProjectSkill> skills = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<ProjectImage> images = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<ProjectLink> links = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "project_id")
    private ArchivedProject archivedProject;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<TeamEvaluation> teamEvaluations = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> members = new ArrayList<>();

    public List<TeamEvaluation> getTeamEvaluations() {
        return teamEvaluations;
    }

    public void setTeamEvaluations(List<TeamEvaluation> teamEvaluations) {
        this.teamEvaluations = teamEvaluations;
    }
}
