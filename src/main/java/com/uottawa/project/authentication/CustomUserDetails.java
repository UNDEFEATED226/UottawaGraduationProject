package com.uottawa.project.authentication;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.uottawa.project.entity.MainMembers;

public class CustomUserDetails implements UserDetails {

	private MainMembers member;

	private Collection<? extends GrantedAuthority> auth;

	public CustomUserDetails(MainMembers member, Collection<? extends GrantedAuthority> auth) {
		this.member = member;
		this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return auth;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return member.getEmail();
	}

	public Long getMemberId() {
		return member.getId();
	}

	public String getRole() {
		return member.getRole();
	}

}