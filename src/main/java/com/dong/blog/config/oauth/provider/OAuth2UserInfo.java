package com.dong.blog.config.oauth.provider;

import com.dong.blog.model.RoleType;

public interface OAuth2UserInfo {
	String getProviderId();
	String getProvider();
	String getEmail();
	String getName();
	RoleType getRole();
	
}
