package com.sequence.demo.controller;

import com.sequence.demo.dto.ArchivedProjectResponseDto;
import com.sequence.demo.service.ArchivedProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Collections;

import com.sequence.demo.type.SortType;

@RestController
@RequestMapping("/api/archive")
@RequiredArgsConstructor
public class ArchivedProjectController {
    
    private final ArchivedProjectService archivedProjectService;
    
    @GetMapping("/projects")
    public ResponseEntity<?> getArchivedProjects() {
        try {
            List<ArchivedProjectResponseDto> projects = archivedProjectService.getArchivedProjects();
            
            if (projects.isEmpty()) {
                return ResponseEntity.ok()
                        .body(Map.of(
                                "message", "조건에 맞는 프로젝트를 찾을 수 없습니다.",
                                "data", Collections.emptyList()
                        ));
            }
            
            return ResponseEntity.ok()
                    .body(Map.of(
                            "message", "아카이브 프로젝트 리스트 조회에 성공했습니다.",
                            "data", projects
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "서버 오류가 발생했습니다.",
                            "data", Collections.emptyList()
                    ));
        }
    }

    @GetMapping("/projects/search")
    public ResponseEntity<?> searchArchivedProjects(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "CREATED") String sortType) {
        try {
            SortType sort = switch (sortType.toUpperCase()) {
                case "CREATED" -> SortType.CREATED_AT;
                case "VIEW" -> SortType.VIEW_COUNT;
                case "BOOKMARK" -> SortType.BOOKMARK_COUNT;
                default -> throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
            };
            List<ArchivedProjectResponseDto> projects = archivedProjectService.searchArchivedProjects(keyword, sort);
            
            if (projects.isEmpty()) {
                return ResponseEntity.ok()
                        .body(Map.of(
                                "message", "검색 결과가 없습니다..",
                                "data", Collections.emptyList()
                        ));
            }
            
            return ResponseEntity.ok()
                    .body(Map.of(
                            "message", "검색 결과를 성공적으로 조회했습니다.",
                            "data", projects
                    ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message", "잘못된 정렬 기준입니다.",
                            "data", Collections.emptyList()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "서버 오류가 발생했습니다.",
                            "data", Collections.emptyList()
                    ));
        }
    }

    @GetMapping("/projects/{sortType}")
    public ResponseEntity<?> getArchivedProjectsBySort(@PathVariable String sortType) {
        try {
            SortType sort = switch (sortType.toUpperCase()) {
                case "CREATED" -> SortType.CREATED_AT;
                case "VIEW" -> SortType.VIEW_COUNT;
                case "BOOKMARK" -> SortType.BOOKMARK_COUNT;
                default -> throw new IllegalArgumentException("잘못된 정렬 기준입니다.");
            };

            List<ArchivedProjectResponseDto> projects = archivedProjectService.getArchivedProjectsBySort(sort);
            
            if (projects.isEmpty()) {
                return ResponseEntity.ok()
                        .body(Map.of(
                                "message", "조건에 맞는 프로젝트를 찾을 수 없습니다.",
                                "data", Collections.emptyList()
                        ));
            }
            
            return ResponseEntity.ok()
                    .body(Map.of(
                            "message", "프로젝트 리스트 조회에 성공했습니다.",
                            "data", projects
                    ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message", "잘못된 정렬 기준입니다.",
                            "data", Collections.emptyList()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "서버 오류가 발생했습니다.",
                            "data", Collections.emptyList()
                    ));
        }
    }
} 