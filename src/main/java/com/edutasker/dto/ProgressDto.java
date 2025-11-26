package com.edutasker.dto;

import java.util.List;

import com.edutasker.entity.User;

public class ProgressDto {

    private Long projectId;
    private String projectTitle;
    private String description;
    private long totalTasks;
    private long completedTasks;
    private double progressPercentage;
    private Long userid;
    private String name;
   

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<TaskStatusDto> tasks;

    public static class TaskStatusDto {
        public Long taskId;
        public String title;
        public String status;
    }

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(long totalTasks) {
		this.totalTasks = totalTasks;
	}

	public long getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(long completedTasks) {
		this.completedTasks = completedTasks;
	}

	public double getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(double progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public List<TaskStatusDto> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskStatusDto> tasks) {
		this.tasks = tasks;
	}

}
