package com.sequence.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class TeamEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evaluationId;

    @Column(name = "project_id", nullable = false)
    private int projectId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = true, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT 'FALSE=작성중, TRUE=완료'")
    private boolean evaluationStatus;

    @Column(nullable = true)
    private String feedback;

    @Column(columnDefinition = "JSON")
    private String keyword;

    @Column(nullable = true)
    private String lineFeedback;

    @Column(nullable = false)
    private LocalDate evaluationDate;

    // Getters and Setters
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
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

    @JsonIgnore
    public boolean isCompleted() {
        return evaluationStatus;
    }
}
