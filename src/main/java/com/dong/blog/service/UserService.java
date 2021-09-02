package com.dong.blog.service;



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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.dong.blog.model.KakaoProfile;
import com.dong.blog.model.OAuthToken;
import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌 . loc를 해준다.
@Service
public class UserService {
	@Value("${defaultPassword.key}")
	private String defaultPasswordKey;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Transactional
	public void 회원가입(User user) {
		System.out.println("==================UserService::회원가입");
		
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	@Transactional
	public void 회원수정(User  requestUser) {
		System.out.println("=============="+requestUser.toString());
		System.out.println("=============="+requestUser.getNo());
		System.out.println("=============="+userRepository.findById(requestUser.getNo()).toString());
		User user = userRepository.findById(requestUser.getNo()).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));
		if(!user.getRole().equals(RoleType.OAUTHUSER)) {
		user.setEmail(requestUser.getEmail());
		user.setUserName(requestUser.getUserName());
		user.setPassword(encoder.encode(requestUser.getPassword()));
		}
		
	}
	@Transactional(readOnly = true)
	public User 회원찾기(User requestUser) {
		System.out.println("==================UserService::회원찾기");
		User user=  userRepository.findByUserId(requestUser.getUserId()).orElseGet(()->null);
		return user;
		
	}
	
	@Transactional(readOnly = true)
	public int 아이디확인(User requestUser) {
		System.out.println("==================UserService::아이디확인::"+requestUser.getUserId()+"\t"+userRepository.findByUserId(requestUser.getUserId()));
		if(userRepository.findByUserId(requestUser.getUserId()).orElseGet(()->null) == null )return 1;
		else return 0;
	}
	@Transactional(readOnly = true)
	public int 별명확인(User requestUser) {
		if(userRepository.findByUserName(requestUser.getUserName()).orElseGet(()->null) == null )return 1;
		else return 0;
	}
	@Transactional
	public void 회원세션변경(User user) {
		
		Authentication authentication;
		authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
	
	@Transactional
	public OAuthToken kakao토큰받기(String code) {

		
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
		
		return oauthToken;
		
		
	}
	@Transactional
	public KakaoProfile user정보받기(OAuthToken oauthToken) {


		
		RestTemplate restTemplate =new RestTemplate();
		// http 해더 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization","Bearer "+oauthToken.getAccess_token());
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
	
		
		// http 해더와 바디를 하나의 오브젝트에 담기
		//ResponseEntity::exchange함수가 HttpEntity를 받기 때문에 하나의 오브젝트에 담는다
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
				new HttpEntity<>(headers);
		
		//Http 요청하기 - 토큰 반환 받을 주소, post 방식, 그리고 해더와 바디가 포함된 오브젝트, 반환 받을 종류
		ResponseEntity<String> response = 
				restTemplate.exchange("https://kapi.kakao.com/v2/user/me"
						,HttpMethod.POST
						,kakaoProfileRequest
						,String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper.readValue( response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kakaoProfile;
		 
	}
	
	@Transactional
	public void kakao회원처리(KakaoProfile kakaoProfile) {
		System.out.println( );
		System.out.println(kakaoProfile.getKakao_account().getEmail());
		System.out.println(defaultPasswordKey);
		User kakaoUser = User
					.builder()
					.userId(kakaoProfile.getId().toString())
					.userName(kakaoProfile.getKakao_account().getProfile().getNickname().toString())
					.password(defaultPasswordKey)
					.email(kakaoProfile.getKakao_account().getEmail())
					.role(RoleType.OAUTHUSER)
					.build();
		User originUser = 회원찾기(kakaoUser);
		if(originUser==null)회원가입(kakaoUser);
		
		
		System.out.println("***************************************************22222");
		
		  // 회원세션처리	
		회원세션변경(kakaoUser);
	}

	
	
	
	
	
}

//@Transactional(readOnly = true)
//public User 로그인(User user) {
//	System.out.println("=======UserService::로그인::in");
//	User user1 = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//	User user2 = userRepository.login(user.getUsername(),user.getPassword());
//	if(user1.equals(user2))System.out.println(user1 +"\t"+ user2);
//	System.out.println("=======UserService::로그인::out");
//	return user1;
//	return userRepository.login(user.getUsername(),user.getPassword());
//}
