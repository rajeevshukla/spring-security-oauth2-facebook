package com.developervisits.oauth2.dto;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;


@Data
@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsDTO implements UserDetails {
	private static final long serialVersionUID = 5450870467888402052L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "USER_NAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name="EMAIL_ID")
	private String emailId;
	@Column(name = "SOURCE")
	private MEDIUM source;

	@Column(name = "IS_ACCOUNT_NON_EXPIRED", columnDefinition = "smallint default 1")
	private boolean isAccountNonExpired;
	@Column(name = "IS_ACCOUNT_NON_LOCKED", columnDefinition = "smallint default 1")
	private boolean isAccountNonLocked;
	@Column(name = "IS_CREDENTIAL_NON_EXPIRED", columnDefinition = "smallint default 1")
	private boolean isCredentialsNonExpired;
	@Column(name = "IS_ENABLED", columnDefinition = "smallint default 1")
	private boolean isEnabled;

	@ManyToMany(targetEntity = RoleDetailsDTO.class,fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE_MAPPING",joinColumns = @JoinColumn(name="USER_ID")
	,inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
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

	private enum MEDIUM {
		FACEBOOK("facebook"),
		SELF("_native"),
		GOOGLE("google");

		private String source;
		MEDIUM(String medium){
			this.source = medium;
		}

		public String getSource() {
			return source;
		}

	}

}
