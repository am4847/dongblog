package com.dong.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌 . loc를 해준다.
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	

	
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
		User user = userRepository.findById(requestUser.getNo()).orElseThrow(()->new IllegalArgumentException("유저찾기 실패"));
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
