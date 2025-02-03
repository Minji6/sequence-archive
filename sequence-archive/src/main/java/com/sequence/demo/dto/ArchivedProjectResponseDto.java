package com.sequence.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArchivedProjectResponseDto {
    private int projectId;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private String thumbnailUrl;
    private LocalDateTime archivedAt;
    private int viewCount;
    private int bookmarkCount;
    private List<String> skills;
    private List<String> imageUrls;
    private List<String> links;
} 