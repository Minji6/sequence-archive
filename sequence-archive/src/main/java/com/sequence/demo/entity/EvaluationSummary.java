package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EvaluationSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evaluationId;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
