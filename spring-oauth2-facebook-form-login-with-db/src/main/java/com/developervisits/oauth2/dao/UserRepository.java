package com.developervisits.oauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.developervisits.oauth2.model.UserDetailsDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsDTO, String>{

	@Query("select u from UserDetailsDTO u where username=?1")
	public UserDetailsDTO findByUsername(String username);
	
}
