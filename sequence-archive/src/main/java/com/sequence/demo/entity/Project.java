package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
<<<<<<< HEAD
import lombok.ToString;
=======
import lombok.NoArgsConstructor;
>>>>>>> 796c8b149e8528178a3870c6089bac4f9ba66d8f

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

    @ToString.Exclude
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
<<<<<<< HEAD
    private List<ProjectSkill> skills = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectImage> images = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectLink> links = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ArchivedProject archivedProject;
=======
    private List<ProjectLink> links;

    @OneToOne(mappedBy = "teamEvaluation", cascade = CascadeType.ALL)
    private TeamEvaluation teamEvaluation;

    public TeamEvaluation getTeamEvaluation() {
        return teamEvaluation;
    }

    public void setTeamEvaluation(TeamEvaluation teamEvaluation) {
        this.teamEvaluation = teamEvaluation;
    }
>>>>>>> 796c8b149e8528178a3870c6089bac4f9ba66d8f
}
