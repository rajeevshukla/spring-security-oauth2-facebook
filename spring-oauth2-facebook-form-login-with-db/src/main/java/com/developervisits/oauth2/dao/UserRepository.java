package com.developervisits.oauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.developervisits.oauth2.dto.UserDetailsDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsDTO, String>{

	@Query("select u from UserDetailsDTO u where username=?1")
	public UserDetailsDTO findByUsername(String username);
	
	@Query("select u from UserDetailsDTO u where emailId=?1")
	public UserDetailsDTO findByEmail(String emailId);
	
}
