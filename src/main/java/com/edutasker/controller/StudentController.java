package com.edutasker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edutasker.dto.Response;
import com.edutasker.dto.SubmissionDto;
import com.edutasker.dto.TaskDto;
import com.edutasker.entity.Project;
import com.edutasker.entity.Submission;
import com.edutasker.entity.Task;
import com.edutasker.service.StudentService;


@RestController
@RequestMapping("/Students")
public class StudentController {
	private StudentService studentService;
	

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	@GetMapping("/projects")
	public ResponseEntity<Response<List<Project>>> getProjects(){
		List<Project> list=studentService.getProjects();
		if(!list.isEmpty()) {
			Response<List<Project>> res=new Response<>(list,"Success");
			return new ResponseEntity<Response<List<Project>>>(res,HttpStatus.OK);
		}
		else {
			Response<List<Project>> res=new Response<>(null,"Failed to get");
			return new ResponseEntity<Response<List<Project>>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
	 // CREATE Task
    @PostMapping("/tasks/create")
    public ResponseEntity<Response<Task>> createTask(@RequestBody TaskDto dto) {

        Task task = studentService.createTask(dto);
        if(task!=null) {
        	Response<Task> res=new Response<>(task,"Success");
        	return new ResponseEntity<Response<Task>>(res,HttpStatus.CREATED);
        }
        else {
        	Response<Task> res=new Response<>(null,"failed");
        	return new ResponseEntity<Response<Task>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
 
    }

    // UPDATE Task
    @PostMapping("/tasks/update")
    public ResponseEntity<Response<Task>> updateTask(@RequestBody TaskDto dto) {

        Task task = studentService.updateTask(dto);
        if(task!=null) {
        	Response<Task> res=new Response<>(task,"Updated");
        	return new ResponseEntity<Response<Task>>(res,HttpStatus.CREATED);
        }
        else {
        	Response<Task> res=new Response<>(null,"failed");
        	return new ResponseEntity<Response<Task>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
    @GetMapping("/tasks/{projectId}")
    public ResponseEntity<Response<List<Task>>> getTasksByProject(@PathVariable Long projectId) {
        List<Task> tasks = studentService.getTasksByProject(projectId);
        if(!tasks.isEmpty()) {
        	Response<List<Task>> res=new Response<>(tasks,"Sucesss");
        	return new ResponseEntity<Response<List<Task>>>(res,HttpStatus.OK);
        }
        else {
        	Response<List<Task>> res=new Response<>(null,"Sucesss");
        	return new ResponseEntity<Response<List<Task>>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
  
    }

    // DELETE Task
    @PostMapping("/tasks/delete/{taskId}")
    public ResponseEntity<Response<String>> deleteTask(@PathVariable Long taskId) {

        String msg = studentService.deleteTask(taskId);
        if(msg!=null) {
        	Response<String> res=new Response<>(msg,"Deleted");
        	return new ResponseEntity<Response<String>>(res,HttpStatus.OK);
        }
        else {
        	Response<String> res=new Response<>(null,"failed");
        	return new ResponseEntity<Response<String>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    @PatchMapping("/tasks/{taskId}/comment")
    public ResponseEntity<Response<Task>> addComment(@PathVariable Long taskId,@RequestBody String comment)  {
    
        Task updated = studentService.addComment(taskId, comment);
        if(updated!=null) {
        	Response<Task> res=new Response<>(updated,"comment added");
        	return new ResponseEntity<Response<Task>>(res,HttpStatus.CREATED);
        }
        else {
        	Response<Task> res=new Response<>(null,"failed");
        	return new ResponseEntity<Response<Task>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping("/submitproject")
    public ResponseEntity<Response<Submission>> submitProject(@RequestBody SubmissionDto dto) {
    	Submission submission=studentService.submitProject(dto);
    	if(submission!=null) {
        	Response<Submission> res=new Response<>(submission,"Submitted");
        	return new ResponseEntity<Response<Submission>>(res,HttpStatus.CREATED);
        }
        else {
        	Response<Submission> res=new Response<>(null,"Failed");
        	return new ResponseEntity<Response<Submission>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	
       // return ResponseEntity.ok(studentService.submitProject(dto));
    }

}
