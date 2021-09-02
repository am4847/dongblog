package com.dong.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dong.blog.model.KakaoProfile;
import com.dong.blog.model.OAuthToken;
import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//인증이 안된 사용자들이 출입할 수 있는 경로를  /auth/** 허용
//그냥 주소가 / 이면 index.jsp까지 허용
// static이하에 있는 /js/**, /css 등등 처음 화면을 잡아줄 화면이 필요할 링크는 공유
@Controller
public class UserController {
	
		
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
	 	@Autowired
	 	private UserService userService;
	 	
		@GetMapping("/auth/joinForm")
		public String joinForm() {
			return "user/joinForm";
		}
		
		@GetMapping("/auth/loginForm")
		public String loginForm() {
			return "user/loginForm";
		}
		
		@GetMapping("/user/updateForm")
		public String updateForm() {
			return "user/updateForm";
		}
		
		@GetMapping("/user/detail")
		public String detail() {
			return "user/detail";
		}
		
		@GetMapping("/auth/kakao/callback")
		public String kakaoCallback(String code) {
			
			userService.kakao회원처리( 
					userService.user정보받기( 
							userService.kakao토큰받기(code)));
		
		
			return "redirect:/";
		}
}
