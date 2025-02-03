package com.sequence.demo.repository;

import com.sequence.demo.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
    List<ProjectMember> findByProjectId(int projectId);
    List<ProjectMember> findByUserId(int userId);
} 