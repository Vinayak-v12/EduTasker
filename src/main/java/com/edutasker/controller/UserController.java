package com.edutasker.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edutasker.dto.LoginDto;
import com.edutasker.dto.RegisterDto;
import com.edutasker.entity.User;
import com.edutasker.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/demo")
	public String hello(){
		return "hello";
	}
	@PostMapping("/register")
	public ResponseEntity<Map<String,String>> register(@RequestBody RegisterDto dto){
		System.out.println("hit ");
		User user= new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		User usr=userService.register(user);
		if(usr!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message","Registration Sucessful"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Message","Registration Failed"));
		}	
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestBody LoginDto dto){
		User user= new User();
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		String token =userService.login(user);
		if(token!=null) {
			return  ResponseEntity.status(HttpStatus.OK).body(Map.of("Token",token));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Message","login Failed"));
		}
	}	
}
