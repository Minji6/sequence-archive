package com.sequence.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "users")  // "user"는 예약어일 수 있어서 "users"로 변경
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    // ERD에 따라 필요한 관계만 유지
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TeamEvaluation> teamEvaluations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ProjectMember> projectMemberships = new ArrayList<>();

    // 나머지 필드와 관계는 ERD에 없으므로 제거
}
