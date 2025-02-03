package com.sequence.demo.service;

import com.sequence.demo.entity.EvaluationStatus;
import com.sequence.demo.entity.EvaluationSummary;
import com.sequence.demo.entity.TeamEvaluation;
import com.sequence.demo.entity.Project;
import com.sequence.demo.repository.TeamEvaluationRepository;
import com.sequence.demo.repository.ProjectRepository;
import com.sequence.demo.repository.UserRepository;
import com.sequence.demo.repository.EvaluationSummaryRepository;
import com.sequence.demo.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamEvaluationService {
    private final TeamEvaluationRepository teamEvaluationRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final EvaluationSummaryRepository evaluationSummaryRepository;
    private final ProjectMemberRepository projectMemberRepository;

    // Create
    public TeamEvaluation createEvaluation(int projectId, TeamEvaluation evaluation) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
        
        userRepository.findById(evaluation.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        evaluation.setProjectId(project.getProjectId());
        evaluation.setScore(evaluation.getScore());
        evaluation.setComment(evaluation.getComment());
        evaluation.setEvaluationStatus(false);
        evaluation.setEvaluationDate(LocalDate.now());

        TeamEvaluation savedEvaluation = teamEvaluationRepository.save(evaluation);
        updateEvaluationSummary(projectId);
        return savedEvaluation;
    }

    // Update
    public TeamEvaluation updateEvaluation(int evaluationId, TeamEvaluation updatedEvaluation) {
        TeamEvaluation existingEvaluation = teamEvaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("평가를 찾을 수 없습니다."));

        boolean previousStatus = existingEvaluation.isEvaluationStatus();
        
        existingEvaluation.setEvaluationStatus(updatedEvaluation.isEvaluationStatus());
        existingEvaluation.setFeedback(updatedEvaluation.getFeedback());
        existingEvaluation.setKeyword(updatedEvaluation.getKeyword());
        existingEvaluation.setLineFeedback(updatedEvaluation.getLineFeedback());
        existingEvaluation.setEvaluationDate(updatedEvaluation.getEvaluationDate());

        TeamEvaluation savedEvaluation = teamEvaluationRepository.save(existingEvaluation);

        // 평가 상태가 변경되었을 때만 summary 업데이트
        if (previousStatus != savedEvaluation.isEvaluationStatus()) {
            updateEvaluationSummary(savedEvaluation.getProjectId());
        }

        return savedEvaluation;
    }

    private void updateEvaluationSummary(int projectId) {
        // 프로젝트의 전체 멤버 수 조회
        int totalMembers = projectMemberRepository.findByProjectId(projectId).size();
        
        // 프로젝트의 완료된 평가 수 조회
        List<TeamEvaluation> completedEvaluations = teamEvaluationRepository.findByProjectId(projectId)
                .stream()
                .filter(TeamEvaluation::isEvaluationStatus)
                .toList();
        
        // 각 멤버별 평가 요약 업데이트
        List<TeamEvaluation> projectEvaluations = teamEvaluationRepository.findByProjectId(projectId);
        for (TeamEvaluation evaluation : projectEvaluations) {
            EvaluationSummary summary = evaluationSummaryRepository
                    .findByProjectIdAndUserId(projectId, evaluation.getUserId())
                    .orElse(new EvaluationSummary());
            
            summary.setProjectId(projectId);
            summary.setUserId(evaluation.getUserId());
            summary.setTotalEvaluations(totalMembers - 1); // 자기 자신 제외
            summary.setCompletedEvaluations((int) completedEvaluations.stream()
                    .filter(e -> e.getUserId() == evaluation.getUserId())
                    .count());
            
            // 모든 평가가 완료되었는지 확인
            boolean isCompleted = summary.getCompletedEvaluations() >= summary.getTotalEvaluations();
            summary.setStatus(isCompleted ? EvaluationStatus.COMPLETED : EvaluationStatus.IN_PROGRESS);
            
            evaluationSummaryRepository.save(summary);
        }
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

    // 프로젝트 ID로 평가 목록 조회
    public List<TeamEvaluation> getEvaluationsByProjectId(int projectId) {
        return teamEvaluationRepository.findByProjectId(projectId);
    }
}
