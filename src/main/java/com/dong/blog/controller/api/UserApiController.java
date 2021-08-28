package com.dong.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.dong.blog.config.auth.PrincipalDetail;
import com.dong.blog.dto.ResponseDto;
import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;
import com.dong.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("=================UserApiController::save User::in");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		System.out.println("=================UserApiController::save User::out");
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PutMapping("/user")
	public  ResponseDto<Integer> update(@RequestBody User user) {
		System.out.println("==============UserApiController::update");
		userService.회원수정(user);
		  Authentication authentication = authenticationManager .authenticate(new
		  UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	@PostMapping("/check/userId")
	public int chkId(@RequestBody User requestUser) {
	 return userService.아이디확인(requestUser);
	
		
	}
	
	@PostMapping("/check/userName")
	public int chkUserName(@RequestBody User requestUser) {
		return userService.별명확인(requestUser);
	}
	
}
