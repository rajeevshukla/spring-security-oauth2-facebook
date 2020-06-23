package com.developervisits.oauth2.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.developervisits.oauth2.common.AuthProvider;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsDTO implements UserDetails, OAuth2User {
	private static final long serialVersionUID = 5450870467888402052L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "PROVIDER")
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	@Column(name = "IS_ACCOUNT_NON_EXPIRED", columnDefinition = "smallint default 1")
	private boolean isAccountNonExpired=true;
	@Column(name = "IS_ACCOUNT_NON_LOCKED", columnDefinition = "smallint default 1")
	private boolean isAccountNonLocked=true;
	@Column(name = "IS_CREDENTIAL_NON_EXPIRED", columnDefinition = "smallint default 1")
	private boolean isCredentialsNonExpired=true;
	@Column(name = "IS_ENABLED", columnDefinition = "smallint default 1")
	private boolean isEnabled=true;
	

	@Transient
	private Map<String, Object> attributes;
	
	@ManyToMany(targetEntity = RoleDetailsDTO.class, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE_MAPPING", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Collection<? extends GrantedAuthority> authorities = new HashSet<>();

	              @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return String.valueOf(userId);
	}

}
