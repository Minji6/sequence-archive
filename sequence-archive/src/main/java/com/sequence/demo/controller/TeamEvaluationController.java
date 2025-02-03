package com.sequence.demo.controller;

import com.sequence.demo.entity.TeamEvaluation;
import com.sequence.demo.service.TeamEvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeamEvaluationController {
    private final TeamEvaluationService teamEvaluationService;

    public TeamEvaluationController(TeamEvaluationService teamEvaluationService) {
        this.teamEvaluationService = teamEvaluationService;
    }

    // Create
    @PostMapping("/projects/{projectId}/evaluations")
    public ResponseEntity<?> createProjectEvaluation(
            @PathVariable int projectId,
            @RequestBody TeamEvaluation evaluation) {
        try {
            TeamEvaluation savedEvaluation = teamEvaluationService.createEvaluation(projectId, evaluation);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "팀원 평가가 성공적으로 저장되었습니다.",
                                "data", savedEvaluation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "평가 작성에 필요한 필드가 누락되었습니다."));
        }
    }

    @RequestMapping("/evaluations")
    public class EvaluationSubController {
        @PutMapping("/{evaluationId}")
        public ResponseEntity<TeamEvaluation> updateEvaluation(@PathVariable int evaluationId, @RequestBody TeamEvaluation evaluation) {
            TeamEvaluation updatedEvaluation = teamEvaluationService.updateEvaluation(evaluationId, evaluation);
            return ResponseEntity.ok(updatedEvaluation);
        }

        @DeleteMapping("/{evaluationId}")
        public ResponseEntity<Void> deleteEvaluation(@PathVariable int evaluationId) {
            teamEvaluationService.deleteEvaluation(evaluationId);
            return ResponseEntity.noContent().build();
        }

        @GetMapping
        public ResponseEntity<List<TeamEvaluation>> getAllEvaluations() {
            List<TeamEvaluation> evaluations = teamEvaluationService.getAllEvaluations();
            return ResponseEntity.ok(evaluations);
        }

        @GetMapping("/{evaluationId}")
        public ResponseEntity<TeamEvaluation> getEvaluationById(@PathVariable int evaluationId) {
            TeamEvaluation evaluation = teamEvaluationService.getEvaluationById(evaluationId);
            return ResponseEntity.ok(evaluation);
        }
    }

    @GetMapping("/projects/{projectId}/evaluations")
    public ResponseEntity<?> getProjectEvaluations(@PathVariable int projectId) {
        try {
            List<TeamEvaluation> evaluations = teamEvaluationService.getEvaluationsByProjectId(projectId);
            return ResponseEntity.ok()
                    .body(Map.of("message", "평가 상태를 성공적으로 조회했습니다.",
                                "data", evaluations));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound()
                    .build();
        }
    }
}
