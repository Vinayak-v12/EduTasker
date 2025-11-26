package com.edutasker.dto;
public class ReviewDto {
    private Long submissionId;
    private String feedback;
    private String grade;
    public Boolean getIs_graded() {
		return is_graded;
	}
	public void setIs_graded(Boolean is_graded) {
		this.is_graded = is_graded;
	}
	private Boolean is_graded;
	public Long getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
    
}
