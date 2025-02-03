package com.sequence.demo.repository;

import com.sequence.demo.entity.TeamEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamEvaluationRepository extends JpaRepository<TeamEvaluation, Integer> {
    // 기본 CRUD 메서드들은 JpaRepository에서 제공
    
    // 프로젝트 ID로 평가 목록 조회
    List<TeamEvaluation> findByProjectId(int projectId);
}
