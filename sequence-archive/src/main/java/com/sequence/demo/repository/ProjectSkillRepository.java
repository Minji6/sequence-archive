package com.sequence.demo.repository;

import com.sequence.demo.entity.ProjectSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectSkillRepository extends JpaRepository<ProjectSkill, Integer> {
    List<ProjectSkill> findByProjectId(int projectId);
}
