package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int linkId;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
