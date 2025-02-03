package com.sequence.demo.service;

import com.sequence.demo.entity.TeamEvaluation;
import com.sequence.demo.repository.TeamEvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamEvaluationService {
    private final TeamEvaluationRepository teamEvaluationRepository;

    public TeamEvaluationService(TeamEvaluationRepository teamEvaluationRepository) {
        this.teamEvaluationRepository = teamEvaluationRepository;
    }

    // Create
    public TeamEvaluation createEvaluation(TeamEvaluation evaluation) {
        return teamEvaluationRepository.save(evaluation);
    }

    // Update
    public TeamEvaluation updateEvaluation(int evaluationId, TeamEvaluation updatedEvaluation) {
        TeamEvaluation existingEvaluation = teamEvaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("평가를 찾을 수 없습니다."));

        existingEvaluation.setEvaluationStatus(updatedEvaluation.isEvaluationStatus());
        existingEvaluation.setFeedback(updatedEvaluation.getFeedback());
        existingEvaluation.setKeyword(updatedEvaluation.getKeyword());
        existingEvaluation.setLineFeedback(updatedEvaluation.getLineFeedback());
        existingEvaluation.setEvaluationDate(updatedEvaluation.getEvaluationDate());

        return teamEvaluationRepository.save(existingEvaluation);
    }

    // Delete
    public void deleteEvaluation(int evaluationId) {
        teamEvaluationRepository.deleteById(evaluationId);
    }

    // Get All
    public List<TeamEvaluation> getAllEvaluations() {
        return teamEvaluationRepository.findAll();
    }

    // Get by ID
    public TeamEvaluation getEvaluationById(int evaluationId) {
        return teamEvaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("평가를 찾을 수 없습니다."));
    }
}
