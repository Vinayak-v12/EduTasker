package com.edutasker.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edutasker.entity.User;
import com.edutasker.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userrepo;
	private BCryptPasswordEncoder encoder;
	private AuthenticationManager authmanager;
	private JwtService jwtService;
	
	public UserService(UserRepository userrepo,AuthenticationManager authmanager,JwtService jwtService){
		this.userrepo=userrepo;
		this.encoder=new BCryptPasswordEncoder();
		this.authmanager=authmanager;
		this.jwtService=jwtService;
	}
	
	
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userrepo.save(user);
		
	}
	public String login(User user) {
	Authentication authentication  =authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			Optional<User> opt=userrepo.findbyEmail(user.getEmail());
			if(opt.isPresent()) {
				User checkUser=opt.get();
				if(checkUser.getRole()!=user.getRole()) {
					System.out.println("faking thier role");
					return null;
				}
				else {
					return jwtService.getToken(checkUser);
				}
			}
			else {
				return null;
			}
		}else {
			return null;
		}
	
	}
}
