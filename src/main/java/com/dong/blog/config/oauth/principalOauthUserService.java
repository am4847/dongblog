package com.dong.blog.config.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.dong.blog.config.auth.PrincipalDetail;
import com.dong.blog.config.oauth.provider.FacebookUserInfo;
import com.dong.blog.config.oauth.provider.GoogleUserInfo;
import com.dong.blog.config.oauth.provider.KakaoUserInfo;
import com.dong.blog.config.oauth.provider.NaverUserInfo;
import com.dong.blog.config.oauth.provider.OAuth2UserInfo;
import com.dong.blog.model.KakaoProfile;
import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;
import com.dong.blog.service.UserService;



@Service
public class principalOauthUserService extends DefaultOAuth2UserService {
	
	
	private final String defaultPasswordKey = "default1234";
	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		OAuth2UserInfo oAuth2UserInfo = null;
		System.out.println("=================:"+userRequest.getClientRegistration().getRegistrationId());
		System.out.println("=================:"+oauth2User.getAttributes());
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글로그인요청");
			oAuth2UserInfo= new GoogleUserInfo(oauth2User.getAttributes());
		
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
			System.out.println("페이스북로그인요청");
		
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
			System.out.println("네이버로그인요청");
		
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오로그인요청");
	
			oAuth2UserInfo = new KakaoUserInfo((Map)oauth2User.getAttributes());
			

		}
	
	
		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId();
		String userName = oAuth2UserInfo.getName();
		String userId = provider+"_"+providerId;
		String password = bCryptPasswordEncoder.encode(defaultPasswordKey) ;
		String email = oAuth2UserInfo.getEmail();
		RoleType role =  oAuth2UserInfo.getRole();
		
		
		User user =  User.builder()
				.userId(userId)
				.userName(userName)
				.password(password)
				.email(email)
				.role(role)
				.build();
		System.out.println("=================회원찾기전"+user.toString());
		User originUser = userService.회원찾기(user);
		if(originUser==null) {
			userService.회원가입(user);
			return  new PrincipalDetail(user,oauth2User.getAttributes());
		}else return  new PrincipalDetail(originUser,oauth2User.getAttributes());
			

	}
	
		
	
}
