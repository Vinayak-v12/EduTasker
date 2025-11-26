package com.edutasker.dto;

import java.time.LocalDateTime;

public class TaskDto {
	
    private Long taskId;        // null for create, required for update/delete
    private Long projectId;     // which project this task belongs to
    private String title;
    private String description;
    private String status;      // PENDING, IN_PROGRESS, COMPLETED
    private LocalDateTime deadline;
    

	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    

   
}

