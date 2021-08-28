package com.dong.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dong.blog.model.User;

import lombok.Data;
import lombok.Getter;

@Data
public class PrincipalDetail implements UserDetails{
	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
		System.out.println("==============PrincipalDetail"+user.toString());
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		
		return user.getUserId();
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->"ROLE_"+user.getRole());
		return collectors;
	}

	
	
}
