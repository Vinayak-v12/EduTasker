package com.edutasker.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDto {

    private String title;
    private String description;
    private LocalDateTime deadline;
    private List<Long> assignedStudentIds;  // assigned_to list

    public ProjectDto() {}

    public ProjectDto(String title, String description, LocalDateTime deadline,
                      Long instructorId, List<Long> assignedStudentIds) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.assignedStudentIds = assignedStudentIds;
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<Long> getAssignedStudentIds() {
        return assignedStudentIds;
    }
    public void setAssignedStudentIds(List<Long> assignedStudentIds) {
        this.assignedStudentIds = assignedStudentIds;
    }
}
