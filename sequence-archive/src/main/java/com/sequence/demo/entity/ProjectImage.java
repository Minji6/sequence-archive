package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(nullable = false)
    private String imageUrl;

    @Column(name = "project_id", nullable = false)
    private int projectId;  // Project 엔티티 대신 projectId만 저장
}
