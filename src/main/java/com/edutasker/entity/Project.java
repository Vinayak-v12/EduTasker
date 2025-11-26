package com.edutasker.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDateTime deadline;
	
	@ManyToMany
	@JoinTable(
			name="project_assignment",
			joinColumns=@JoinColumn(name="project_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> assigned_to;
	
	
	@ManyToOne
	@JoinColumn(name="assigned_by")
	private User assigned_by;
	

    // One project has many tasks
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks;
    

    // Submissions of this project
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Submission> submissions;
    
    
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public LocalDateTime getDeadline() {
		return deadline;
	}


	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}


	public List<User> getAssigned_to() {
		return assigned_to;
	}


	public void setAssigned_to(List<User> assigned_to) {
		this.assigned_to = assigned_to;
	}


	public User getAssigned_by() {
		return assigned_by;
	}


	public void setAssigned_by(User assigned_by) {
		this.assigned_by = assigned_by;
	}


	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


	public List<Submission> getSubmissions() {
		return submissions;
	}


	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}



}
