package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ArchivedProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archivedProjectId;

    @OneToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String archivedTitle;

    @Column(nullable = false)
    private LocalDateTime archivedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int viewCount = 0;

    @Column(nullable = false)
    private int bookmarkCount = 0;
}
