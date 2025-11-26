package com.edutasker.security;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.edutasker.entity.User;
import com.edutasker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userrepo;
	
	public CustomUserDetailsService(UserRepository userrepo){
		this.userrepo=userrepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user;
	    Optional<User> opt=userrepo.findbyEmail(email);
	    if(opt.isEmpty()) {
	    	
	    	throw new UsernameNotFoundException("No such user");
	    	
	    }
	    else {
	    	user=opt.get();	
	    }
	    
		return new CustomUserDetails(user);
	}

}
