package com.sequence.demo.controller;

import com.sequence.demo.entity.TeamEvaluation;
import com.sequence.demo.service.TeamEvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class TeamEvaluationController {
    private final TeamEvaluationService teamEvaluationService;

    public TeamEvaluationController(TeamEvaluationService teamEvaluationService) {
        this.teamEvaluationService = teamEvaluationService;
    }

    // Create
    @PostMapping
    public ResponseEntity<TeamEvaluation> createEvaluation(@RequestBody TeamEvaluation evaluation) {
        TeamEvaluation createdEvaluation = teamEvaluationService.createEvaluation(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluation);
    }

    // Update
    @PutMapping("/{evaluationId}")
    public ResponseEntity<TeamEvaluation> updateEvaluation(@PathVariable int evaluationId, @RequestBody TeamEvaluation evaluation) {
        TeamEvaluation updatedEvaluation = teamEvaluationService.updateEvaluation(evaluationId, evaluation);
        return ResponseEntity.ok(updatedEvaluation);
    }

    // Delete
    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable int evaluationId) {
        teamEvaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<TeamEvaluation>> getAllEvaluations() {
        List<TeamEvaluation> evaluations = teamEvaluationService.getAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    // Get by ID
    @GetMapping("/{evaluationId}")
    public ResponseEntity<TeamEvaluation> getEvaluationById(@PathVariable int evaluationId) {
        TeamEvaluation evaluation = teamEvaluationService.getEvaluationById(evaluationId);
        return ResponseEntity.ok(evaluation);
    }
}
