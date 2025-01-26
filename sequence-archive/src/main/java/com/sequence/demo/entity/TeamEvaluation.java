package com.sequence.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TeamEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evaluationId; // 평가 ID

    @Column(nullable = false)
    private int projectId; // 프로젝트 ID (연관 관계로 설정 가능)

    @Column(nullable = true, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT 'FALSE=작성중, TRUE=완료'")
    private boolean evaluationStatus; // 평가 상태 (FALSE = 작성 중, TRUE = 완료)

    @Column(nullable = true)
    private String feedback; // 피드백

    @Column(columnDefinition = "JSON")
    private String keyword; // JSON 형식의 키워드

    @Column(nullable = true)
    private String lineFeedback; // 한줄평

    @Column(nullable = false)
    private LocalDate evaluationDate; // 평가 날짜

    @OneToOne
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // Getters and Setters
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(boolean evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLineFeedback() {
        return lineFeedback;
    }

    public void setLineFeedback(String lineFeedback) {
        this.lineFeedback = lineFeedback;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
}
