package com.dong.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.dong.blog.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PrincipalDetail implements UserDetails, OAuth2User {
	
	private User user;
	private  Map<String, Object> attributes;
	
	
	public PrincipalDetail(User user) {
		this.user = user;
		System.out.println("==============PrincipalDetail"+user.toString());
	}

	public PrincipalDetail(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
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

	@Override
	public Map<String, Object> getAttributes() {
		
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	

	

	
	
}
