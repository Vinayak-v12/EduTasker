package com.edutasker.dto;

public class SubmissionDto {
	 private Long projectId;
	 private String content; // URL / text / code
	  
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	  
	  
}
