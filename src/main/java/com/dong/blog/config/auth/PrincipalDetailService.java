package com.dong.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	
	
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("해당사용자를 찾을수 없습니다:"+username));
		return new PrincipalDetail(principal);
	}
 
}
