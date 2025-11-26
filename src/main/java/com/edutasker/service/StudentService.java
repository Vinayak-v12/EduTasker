package com.edutasker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.edutasker.dto.SubmissionDto;
import com.edutasker.dto.TaskDto;
import com.edutasker.entity.Project;
import com.edutasker.entity.Submission;
import com.edutasker.entity.Task;
import com.edutasker.entity.TaskStatus;
import com.edutasker.entity.User;
import com.edutasker.repository.ProjectRepository;
import com.edutasker.repository.SubmissionRepository;
import com.edutasker.repository.TaskRepository;
import com.edutasker.repository.UserRepository;
import com.edutasker.security.CustomUserDetails;

@Service
public class StudentService {
	
	private ProjectRepository projectrepo;
	private UserRepository userrepo;
	private TaskRepository taskRepo;
	private SubmissionRepository submissionRepo;
	
	
	public StudentService(ProjectRepository projectrepo, UserRepository userrepo, TaskRepository taskRepo,SubmissionRepository submissionRepo) {
		this.projectrepo = projectrepo;
		this.userrepo = userrepo;
		this.taskRepo = taskRepo;
		this.submissionRepo=submissionRepo;
	}

	public Long getLoggedStudentId() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof CustomUserDetails userDetails) {
	        return userDetails.getId();  // logged Studennt/user ID
	    }
	    else {
	    	throw new RuntimeException("User not found in context");
	    }

	  
	}
	
	public List<Project> getProjects() {
		Long studentId= getLoggedStudentId();
		return userrepo.assignedProjects(studentId);
		
	}
	 // Check whether this student is assigned to this project
    private Project validateProjectAccess(Long projectId) {
        Long studentId = getLoggedStudentId();

        Project project = projectrepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        boolean assigned = project.getAssigned_to()
                .stream()
                .anyMatch(s -> s.getId() == studentId);

        if (!assigned) {
            throw new RuntimeException("You are not allowed to modify tasks for this project");
        }

        return project;
    }

    // CREATE task
    public Task createTask(TaskDto dto) {
        Project project = validateProjectAccess(dto.getProjectId());
        Long id=getLoggedStudentId();
        Optional<User> opt=userrepo.findById(id);
        User student;
        if(opt.isPresent()) 
        	student=opt.get();
        else {
        	throw new RuntimeException("User not found in context");
        }
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(TaskStatus.valueOf(dto.getStatus()));
        task.setProject(project);
        task.setDeadline(dto.getDeadline());
        task.setStudent(student);
        return taskRepo.save(task);
    }
    public List<Task> getTasksByProject(Long projectId) {
        Project project = projectrepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return taskRepo.findByProject(project);
    }

    // UPDATE task
    public Task updateTask(TaskDto dto) {
        Task task = taskRepo.findById(dto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validateProjectAccess(task.getProject().getId());

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(TaskStatus.valueOf(dto.getStatus()));

        return taskRepo.save(task);
    }

    // DELETE task
    public String deleteTask(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validateProjectAccess(task.getProject().getId());

        taskRepo.delete(task);

        return "Task deleted successfully";
    }
    // add comment
    public Task addComment(Long taskId, String comment) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setComment(comment);
        return taskRepo.save(task);
    }
    //submit project
    public Submission submitProject(SubmissionDto dto) {

    	long studentId = getLoggedStudentId();

    // 1. Validate project
    	Project project;
    	Optional<Project> opt = projectrepo.findById(dto.getProjectId());
    	if(opt.isEmpty()){
    		throw new RuntimeException("Project not found");
    	}
    	else {
    		project=opt.get();
    	}

    	boolean assigned = project.getAssigned_to()
                    .stream()
                    .anyMatch(s -> s.getId()==studentId);

    	if (!assigned) {
    		throw new RuntimeException("You are not assigned to this project");
    	}

    	// 2. Fetch student
    	User student = userrepo.findById(studentId)
    			.orElseThrow(() -> new RuntimeException("User not found"));

    	// 3. Create submission
    	Submission submission = new Submission();
    	submission.setProject(project);
    	submission.setStudent(student);
    	submission.setContent(dto.getContent());
    	submission.setSubmittedAt(LocalDate.now());  

    return submissionRepo.save(submission);
    
    
    }

}
