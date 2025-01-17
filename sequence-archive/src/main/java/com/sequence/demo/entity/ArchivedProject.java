package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ArchivedProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archivedProjectId;

    @Column(nullable = false)
    private String archivedTitle;

    @Column(nullable = false)
    private String archivedDescription;
}
