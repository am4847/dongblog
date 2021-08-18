package com.dong.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dong.blog.dto.ResponseDto;
import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;
import com.dong.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	UserService userService;
	

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("=================UserApiController::save User::in");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		System.out.println("=================UserApiController::save User::out");
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}
