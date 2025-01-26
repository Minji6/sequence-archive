package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectSkill> skills;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectImage> images;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectLink> links;

    @OneToOne(mappedBy = "teamEvaluation", cascade = CascadeType.ALL)
    private TeamEvaluation teamEvaluation;

    public TeamEvaluation getTeamEvaluation() {
        return teamEvaluation;
    }

    public void setTeamEvaluation(TeamEvaluation teamEvaluation) {
        this.teamEvaluation = teamEvaluation;
    }
}
