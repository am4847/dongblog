package com.dong.blog.config.oauth.provider;

import java.util.Map;

import com.dong.blog.model.RoleType;

public class FacebookUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;
	
	public FacebookUserInfo(Map<String, Object> attributes) {
				this.attributes = attributes;
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return (String) attributes.get("id");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "FACEBOOK";
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
		return RoleType.FACKBOOK_OAUTHUSER;
	}

}
