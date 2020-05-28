package com.developervisits.oauth2.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "ROLE_DETAILS")
public class RoleDetailsDTO implements GrantedAuthority{

	private static final long serialVersionUID = -5092076720008287704L;
	@Id
	@Column(name = "ROLE_ID")
	private String roleId;
	
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	@Override
	public String getAuthority() {
		return roleId;
	}
}
