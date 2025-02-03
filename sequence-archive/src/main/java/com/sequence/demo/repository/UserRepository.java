package com.sequence.demo.repository;

import com.sequence.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 기본 CRUD 메서드들은 JpaRepository에서 제공됩니다
} 