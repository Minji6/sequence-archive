package com.sequence.demo.service;

import com.sequence.demo.dto.ArchivedProjectResponseDto;
import com.sequence.demo.entity.ArchivedProject;
import com.sequence.demo.entity.Project;
import com.sequence.demo.entity.ProjectImage;
import com.sequence.demo.entity.ProjectLink;
import com.sequence.demo.entity.Skill;
import com.sequence.demo.repository.ArchivedProjectRepository;
import com.sequence.demo.repository.ProjectSkillRepository;
import com.sequence.demo.repository.SkillRepository;
import com.sequence.demo.type.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArchivedProjectService {
    private final ArchivedProjectRepository archivedProjectRepository;
    private final ProjectSkillRepository projectSkillRepository;
    private final SkillRepository skillRepository;

    public List<ArchivedProjectResponseDto> getArchivedProjects() {
        return archivedProjectRepository.findAllDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ArchivedProjectResponseDto> searchArchivedProjects(String keyword, SortType sortType) {
        List<ArchivedProject> archivedProjects = switch (sortType) {
            case CREATED_AT -> archivedProjectRepository.findByKeywordOrderByCreatedAtAsc(keyword);
            case VIEW_COUNT -> archivedProjectRepository.findByKeywordOrderByViewCountDesc(keyword);
            case BOOKMARK_COUNT -> archivedProjectRepository.findByKeywordOrderByBookmarkCountDesc(keyword);
            default -> archivedProjectRepository.findByKeyword(keyword);
        };

        return archivedProjects.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ArchivedProjectResponseDto> getArchivedProjectsBySort(SortType sortType) {
        List<ArchivedProject> archivedProjects = switch (sortType) {
            case CREATED_AT -> archivedProjectRepository.findAllOrderByCreatedAtAsc();
            case VIEW_COUNT -> archivedProjectRepository.findAllOrderByViewCountDesc();
            case BOOKMARK_COUNT -> archivedProjectRepository.findAllOrderByBookmarkCountDesc();
            default -> archivedProjectRepository.findAllDesc();
        };

        return archivedProjects.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ArchivedProjectResponseDto convertToDto(ArchivedProject archivedProject) {
        Project project = archivedProject.getProject();
        ArchivedProjectResponseDto dto = new ArchivedProjectResponseDto();
        
        dto.setProjectId(project.getProjectId());
        dto.setTitle(archivedProject.getArchivedTitle());
        dto.setDescription(project.getDescription());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setStatus(project.getStatus());
        dto.setThumbnailUrl(project.getThumbnailUrl());
        dto.setArchivedAt(archivedProject.getArchivedAt());
        dto.setViewCount(archivedProject.getViewCount());
        dto.setBookmarkCount(archivedProject.getBookmarkCount());
        
        List<String> skillNames = projectSkillRepository.findByProjectId(project.getProjectId())
            .stream()
            .map(ps -> skillRepository.findById(ps.getSkillId())
                .map(Skill::getName)
                .orElse(""))
            .collect(Collectors.toList());
        dto.setSkills(skillNames);
        
        dto.setImageUrls(project.getImages().stream()
                .map(ProjectImage::getImageUrl)
                .collect(Collectors.toList()));
                
        dto.setLinks(project.getLinks().stream()
                .map(ProjectLink::getUrl)
                .collect(Collectors.toList()));
                
        return dto;
    }
} 