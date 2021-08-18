package com.dong.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌 . loc를 해준다.
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public User 로그인(User user) {
		System.out.println("=======UserService::로그인::in");
//		User user1 = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//		User user2 = userRepository.login(user.getUsername(),user.getPassword());
//		if(user1.equals(user2))System.out.println(user1 +"\t"+ user2);
//		System.out.println("=======UserService::로그인::out");
//		return user1;
		return userRepository.login(user.getUsername(),user.getPassword());
	}
}
