package com.dong.blog.config.oauth.provider;

import java.util.Map;

import com.dong.blog.model.RoleType;

public class GoogleUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;

	public GoogleUserInfo(Map<String, Object> attributes) {
				this.attributes = attributes;
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return (String) attributes.get("sub");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "GOOGLE";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attributes.get("name");
	}

	

	@Override
	public RoleType getRole() {
		// TODO Auto-generated method stub
		return RoleType.GOOGLE_OAUTHUSER;
	}

}
