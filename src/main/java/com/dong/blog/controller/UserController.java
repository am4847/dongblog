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
	
		@Value("${defaultPassword.key}")
		private String defaultPasswordKey;
		
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
			
			
			
			
			RestTemplate restTemplate =new RestTemplate();
			
			// http 해더 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
			
			// http 바디 오브젝트 생성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "a51945892037291a79698727ae1fad25");
			params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
			params.add("code", code);
			
			// http 해더와 바디를 하나의 오브젝트에 담기
			//ResponseEntity::exchange함수가 HttpEntity를 받기 때문에 하나의 오브젝트에 담는다
			HttpEntity<MultiValueMap<String, String>> kakaoTokenRequst = 
					new HttpEntity<>(params,headers);
			
			//Http 요청하기 - 토큰 반환 받을 주소, post 방식, 그리고 해더와 바디가 포함된 오브젝트, 반환 받을 종류
			ResponseEntity<String> response = 
					restTemplate.exchange("https://kauth.kakao.com/oauth/token"
							,HttpMethod.POST
							,kakaoTokenRequst
							,String.class);
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oauthToken =null;
			try {
				oauthToken = objectMapper.readValue( response.getBody(), OAuthToken.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("*******************"+oauthToken.getAccess_token());
			
			
			
			
			
			RestTemplate restTemplate2 =new RestTemplate();
			// http 해더 오브젝트 생성
			HttpHeaders headers2 = new HttpHeaders();

			headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
			headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
			
		
			
			// http 해더와 바디를 하나의 오브젝트에 담기
			//ResponseEntity::exchange함수가 HttpEntity를 받기 때문에 하나의 오브젝트에 담는다
			HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
					new HttpEntity<>(headers2);
			
			//Http 요청하기 - 토큰 반환 받을 주소, post 방식, 그리고 해더와 바디가 포함된 오브젝트, 반환 받을 종류
			ResponseEntity<String> response2 = 
					restTemplate2.exchange("https://kapi.kakao.com/v2/user/me"
							,HttpMethod.POST
							,kakaoProfileRequest
							,String.class);
			
			ObjectMapper objectMapper2 = new ObjectMapper();
			KakaoProfile kakaoProfile = null;
			try {
				kakaoProfile = objectMapper2.readValue( response2.getBody(), KakaoProfile.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			System.out.println( );
			System.out.println(kakaoProfile.getKakao_account().getEmail());
			System.out.println(defaultPasswordKey);
			User kakaoUser = User
						.builder()
						.username(kakaoProfile.getKakao_account().getEmail().substring(0, kakaoProfile.getKakao_account().getEmail().indexOf("@"))+"_"+kakaoProfile.getId())
						.password(defaultPasswordKey)
						.email(kakaoProfile.getKakao_account().getEmail())
						.role(RoleType.OAUTHUSER)
						.build();
			User originUser = userService.회원찾기(kakaoUser);
			if(originUser==null)userService.회원가입(kakaoUser);
			
			
			System.out.println("***************************************************22222");
			
			  // 로그인처리
			
			Authentication authentication = authenticationManager .authenticate(new
			UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUser.getPassword()));
			System.out.println("***************************************************333333");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("***************************************************4444");
			return "redirect:/";
		}
}
