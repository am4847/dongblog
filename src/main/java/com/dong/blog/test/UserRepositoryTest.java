package com.dong.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;

@RestController
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/test/findByUserId")
	public User findId() {
		String username ="love";
		User principal = userRepository.findByUserId(username)
				.orElseThrow(()->new UsernameNotFoundException("해당사용자를 찾을수 없습니다:"+username));
		return principal;
		
	}
	
}
