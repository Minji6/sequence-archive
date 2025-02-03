package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "evaluation_summary")
public class EvaluationSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    private int summaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private int projectId;

    @Column(name = "total_evaluatoins", nullable = false)
    private int totalEvaluations;

    @Column(name = "completed_evaluations", nullable = false)
    private int completedEvaluations;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private com.sequence.demo.entity.EvaluationStatus status;
} 