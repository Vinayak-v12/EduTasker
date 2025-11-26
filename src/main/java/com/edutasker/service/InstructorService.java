package com.edutasker.service;

import org.springframework.stereotype.Service;

import com.edutasker.dto.ProgressDto;
import com.edutasker.dto.ProjectDto;
import com.edutasker.dto.ProjectFeedbackDto;
import com.edutasker.dto.ReviewDto;
import com.edutasker.dto.subDto;
import com.edutasker.entity.Project;
import com.edutasker.entity.Submission;
import com.edutasker.entity.Task;
import com.edutasker.entity.TaskStatus;
import com.edutasker.entity.User;
import com.edutasker.repository.ProjectRepository;
import com.edutasker.repository.SubmissionRepository;
import com.edutasker.repository.UserRepository;
import com.edutasker.security.CustomUserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class InstructorService {
	
	private ProjectRepository projectrepo;
	private UserRepository userrepo;
	private SubmissionRepository subrepo;
	
	public InstructorService(ProjectRepository projectrepo, UserRepository userrepo,SubmissionRepository subrepo) {
		this.projectrepo = projectrepo;
		this.userrepo = userrepo;
		this.subrepo=subrepo;
	}
	public Long getLoggedInstructorId() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof CustomUserDetails userDetails) {
	        return userDetails.getId();  // your instructor/user ID
	    }
	    else {
	    	throw new RuntimeException("User not found in context");
	    }

	  
	}
	public Project createProject(Project project,ProjectDto dto) {
		User user;
		Long id= getLoggedInstructorId();
		Optional<User> opt=userrepo.findById(id);
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("username not found");
		}
		else {
			 user=opt.get();
		}
		List<User> students=userrepo.findAllById(dto.getAssignedStudentIds());
		project.setAssigned_by(user);
		project.setAssigned_to(students);
		return projectrepo.save(project);
	}
	public List<Project> getAllProjects(){
		
		Long id= getLoggedInstructorId();
		return projectrepo.createdByInstructor(id);
		
	}
	public List<ProgressDto> trackprogress(){
		long instructorId = getLoggedInstructorId();
	    List<Project> projects = projectrepo.createdByInstructor(instructorId);

	    List<ProgressDto> DtoList = new ArrayList<>();

	    for (Project proj : projects) {

	        for (User usr : proj.getAssigned_to()) {

	        
	            List<Task> studentTasks = proj.getTasks().stream()
	                    .filter(task -> task.getStudent() != null &&
	                            task.getStudent().getId() == usr.getId())
	                    .toList();

	            ProgressDto dto = new ProgressDto();
	            dto.setProjectId(proj.getId());
	            dto.setProjectTitle(proj.getTitle());
	            dto.setDescription(proj.getDescription());
	            dto.setName(usr.getName());
	            dto.setUserid(usr.getId());

	            long totalTask = studentTasks.size();
	            dto.setTotalTasks(totalTask);

	            long completed = studentTasks.stream()
	                    .filter(t -> t.getStatus() == TaskStatus.COMPLETED)
	                    .count();
	            dto.setCompletedTasks(completed);

	            double percentage = (totalTask == 0 ? 0 : (completed * 100.0 / totalTask));
	            dto.setProgressPercentage(percentage);

	            dto.setTasks(
	                studentTasks.stream().map(task -> {
	                    ProgressDto.TaskStatusDto t = new ProgressDto.TaskStatusDto();
	                    t.taskId = task.getId();
	                    t.title = task.getTitle();
	                    t.status = task.getStatus().toString();
	                    return t;
	                }).toList()
	            );

	            DtoList.add(dto);
	        }
	    }
	    return DtoList;
	}
	public List<subDto> getSubmissions() {
	    Long id = getLoggedInstructorId();
	    List<Submission> submissions = subrepo.findSubmissionsForInstructor(id);

	    return submissions.stream().map(s -> {
	        subDto dto = new subDto();
	        dto.setId(s.getId());
	        dto.setContent(s.getContent());
	        dto.setStudentName(s.getStudent().getName());
	        dto.setStudentId(s.getStudent().getId());
	        dto.setProjectId(s.getProject().getId());
	        dto.setProjectTitle(s.getProject().getTitle());
	        return dto;
	    }).toList();
	}

	public Submission reviewProject(long projectId, ReviewDto dto) {

	    // Check project exists
	    projectrepo.findById(projectId)
	            .orElseThrow(() -> new RuntimeException("Project not found"));

	    // Find submission by ID
	    Submission submission = subrepo.findById(dto.getSubmissionId())
	            .orElseThrow(() -> new RuntimeException("Submission not found"));

	    // Update details
	    submission.setFeedback(dto.getFeedback());
	    submission.setGrade(dto.getGrade());
	    submission.setIs_graded(dto.getIs_graded());

	    return subrepo.save(submission);
	}


}
