package com.sequence.demo.repository;

import com.sequence.demo.entity.ArchivedProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivedProjectRepository extends JpaRepository<ArchivedProject, Integer> {
    // 기본 검색 (제목) - 최신순
    @Query("SELECT ap FROM ArchivedProject ap WHERE LOWER(ap.archivedTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY ap.createdAt DESC")
    List<ArchivedProject> findByKeyword(String keyword);
    
    // 검색 + 작성일순 (오래된순)
    @Query("SELECT ap FROM ArchivedProject ap WHERE LOWER(ap.archivedTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY ap.createdAt ASC")
    List<ArchivedProject> findByKeywordOrderByCreatedAtAsc(String keyword);
    
    // 검색 + 조회수순
    @Query("SELECT ap FROM ArchivedProject ap WHERE LOWER(ap.archivedTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY ap.viewCount DESC")
    List<ArchivedProject> findByKeywordOrderByViewCountDesc(String keyword);
    
    // 검색 + 북마크수순
    @Query("SELECT ap FROM ArchivedProject ap WHERE LOWER(ap.archivedTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY ap.bookmarkCount DESC")
    List<ArchivedProject> findByKeywordOrderByBookmarkCountDesc(String keyword);
    
    // 기본 리스트 조회 - 최신순
    @Query("SELECT ap FROM ArchivedProject ap ORDER BY ap.createdAt DESC")
    List<ArchivedProject> findAllDesc();
    
    // 작성일순 (오래된순)
    @Query("SELECT ap FROM ArchivedProject ap ORDER BY ap.createdAt ASC")
    List<ArchivedProject> findAllOrderByCreatedAtAsc();
    
    // 조회수순
    @Query("SELECT ap FROM ArchivedProject ap ORDER BY ap.viewCount DESC")
    List<ArchivedProject> findAllOrderByViewCountDesc();
    
    // 북마크수순
    @Query("SELECT ap FROM ArchivedProject ap ORDER BY ap.bookmarkCount DESC")
    List<ArchivedProject> findAllOrderByBookmarkCountDesc();
} 