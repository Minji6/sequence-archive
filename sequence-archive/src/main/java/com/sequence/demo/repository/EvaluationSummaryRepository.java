package com.sequence.demo.repository;

import com.sequence.demo.entity.EvaluationSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationSummaryRepository extends JpaRepository<EvaluationSummary, Integer> {
    List<EvaluationSummary> findByProjectId(int projectId);
    List<EvaluationSummary> findByUserId(int userId);
    Optional<EvaluationSummary> findByProjectIdAndUserId(int projectId, int userId);
} 