package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "project_id", nullable = false)
    private int projectId;

    @Column(name = "skill_id", nullable = false)
    private int skillId;
}
