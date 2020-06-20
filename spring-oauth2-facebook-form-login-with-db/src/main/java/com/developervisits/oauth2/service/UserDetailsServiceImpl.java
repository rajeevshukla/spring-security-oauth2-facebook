package com.developervisits.oauth2.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.developervisits.oauth2.common.AuthProvider;
import com.developervisits.oauth2.dao.UserRepository;
import com.developervisits.oauth2.dto.RoleDetailsDTO;
import com.developervisits.oauth2.dto.UserDetailsDTO;
import com.developervisits.oauth2.model.RegisterUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encode;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("fetching user details");
		UserDetailsDTO userDetails = userRepository.findByUsername(username); 
		System.out.println(userDetails);
		if(userDetails == null) {
			System.out.println("Throwing...");
//			throw new RuntimeException("check it");
			throw new RuntimeException("Username:"+username +" not found!");
		} 
		
		if(userDetails != null && !AuthProvider.local.equals(userDetails.getProvider())) {
			System.out.println("Throwing user provider issue");
			throw new RuntimeException("Username:"+username +" has logged in from "+userDetails.getProvider()+". Please login with this provider");
		}
		return userDetails;
	}
	
	
	public void registerUser(RegisterUser registerUser, AuthProvider provider) {
		
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(registerUser.getUsername());
		userDetailsDTO.setPassword(encode.encode(registerUser.getPassword()));
		userDetailsDTO.setFirstName(registerUser.getFirstName());
		userDetailsDTO.setLastName(registerUser.getLastName());
		userDetailsDTO.setEmailId(registerUser.getEmail());
		RoleDetailsDTO roleDetails = new RoleDetailsDTO();
		roleDetails.setRoleId("ROLE_USER");
		HashSet<RoleDetailsDTO> roleSet = new HashSet<>();
		userDetailsDTO.setAuthorities(roleSet);
		//When user registers through application. Provider would be Local
		userDetailsDTO.setProvider(provider);
		
		userRepository.save(userDetailsDTO);
	}

}
