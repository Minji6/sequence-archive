package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;

    @Column(nullable = false)
    private String name;
}
