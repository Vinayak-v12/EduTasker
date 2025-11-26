package com.edutasker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutasker.dto.ProgressDto;
import com.edutasker.dto.ProjectDto;
import com.edutasker.dto.ProjectFeedbackDto;
import com.edutasker.dto.Response;
import com.edutasker.dto.ReviewDto;
import com.edutasker.dto.subDto;
import com.edutasker.entity.Project;
import com.edutasker.entity.Submission;
import com.edutasker.service.InstructorService;

@RestController
@RequestMapping("/Instructor")
public class InstructorController {
	
	public InstructorController(InstructorService service) {
		this.service = service;
	}

	private InstructorService service;
	
	@PostMapping("/create")
	public ResponseEntity<Response<Project>> createProject(@RequestBody ProjectDto dto){
		
		Project project= new Project();
		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setDeadline(dto.getDeadline());
		Project prj=service.createProject(project ,dto);
		if(prj!=null) {
			Response<Project> res= new Response<>(prj,"Created Successfully");
			return new ResponseEntity<Response<Project>>(res, HttpStatus.CREATED);
		}
		else {
			Response<Project> res= new Response<>(null,"Failed");
			return new ResponseEntity<Response<Project>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/projects")
	public ResponseEntity<Response<List<Project>>> getAllProjects(){
		List<Project> list =service.getAllProjects();
		if(!list.isEmpty()) {
			Response<List<Project>> res=new Response<>(list,"Success");
			return new ResponseEntity<Response<List<Project>>>(res,HttpStatus.OK);
		}
		else {
			Response<List<Project>> res=new Response<>(null,"Failed to get");
			return new ResponseEntity<Response<List<Project>>>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/task/status")
	public ResponseEntity<Response<List<ProgressDto>>> trackProgress(){
		List<ProgressDto> list=service.trackprogress();
		if(!list.isEmpty()) {
			Response<List<ProgressDto>> res=new Response<>(list,"Success");
			return new ResponseEntity<Response<List<ProgressDto>>> (res,HttpStatus.OK);
		}
		else {
			Response<List<ProgressDto>> res=new Response<>(null,"Failed to get");
			return new ResponseEntity<Response<List<ProgressDto>>> (res,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/submissions")
	public ResponseEntity<List<subDto>> submissions() {
	    return ResponseEntity.ok(service.getSubmissions());
	}

	   
		
	@PatchMapping("/project/{projectId}/review")
	public ResponseEntity<Submission> reviewProject(
	        @PathVariable Long projectId,
	        @RequestBody ReviewDto dto) {

	    return ResponseEntity.ok(service.reviewProject(projectId, dto));
	}



	

}
