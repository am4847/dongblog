package com.dong.blog.config.oauth.provider;

import java.util.Map;

import com.dong.blog.model.RoleType;

public class NaverUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;

	
	
	public NaverUserInfo(Map<String, Object> attributes) {
		super();
		this.attributes = attributes;
		System.out.println(this.attributes.toString());
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return (String) attributes.get("id");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "NAVER";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return  (String) attributes.get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return  (String) attributes.get("nickname");
	}

	@Override
	public RoleType getRole() {
		// TODO Auto-generated method stub
		return RoleType.NAVER_OAUTHUSER;
	}
	

}
