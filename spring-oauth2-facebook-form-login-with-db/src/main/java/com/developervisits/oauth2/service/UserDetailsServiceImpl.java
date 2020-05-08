package com.developervisits.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.developervisits.oauth2.dao.UserRepository;
import com.developervisits.oauth2.dto.UserDetailsDTO;
import com.developervisits.oauth2.model.RegisterUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.findByUsername(username); 
		
		if(userDetails == null) {
			throw new UsernameNotFoundException("Username:"+username +" not found!");
		}
		return userDetails;
	}
	
	
	
	public void registerUser(RegisterUser registerUser) {
		
		
		
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(registerUser.getUsername());
		
		
		userRepository.save(userDetailsDTO);
	}

}
