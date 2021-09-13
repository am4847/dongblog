package com.dong.blog.config.oauth.provider;

import java.util.Map;

import com.dong.blog.model.RoleType;

public class KakaoUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;

	
	
	public KakaoUserInfo(Map<String, Object> attributes) {
		System.out.println("=============KakaoUserInfo:in");
		this.attributes = attributes;
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return attributes.get("id").toString();
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "KAKAO";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		Map<String, Object> kakao = (Map) attributes.get("kakao_account");
		return  (String) kakao.get("email");
	}

	@Override
	public String getName() {
		Map<String, Object> kakao = (Map) attributes.get("properties");
		return  (String) kakao.get("nickname");
	}

	@Override
	public RoleType getRole() {
		// TODO Auto-generated method stub
		return RoleType.KAKAO_OAUTHUSER;
	}
	
}
