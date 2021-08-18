package com.dong.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //의존성주입! DI
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 아이디는 없습니다.";
		}
	
		return "삭제되었습니다";
		
	}
	
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		
		User user =userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당유저는 없습니다."));
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		return user;
		
		
	}
	@GetMapping("/dummy/user/all")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	
	  @GetMapping("/dummy/user") 
	  public Page<User>pageList(@PageableDefault(size=1,sort = "id", direction = Sort.Direction.DESC) Pageable pageable){ 
		  return userRepository.findAll(pageable); 
	  }
	 
	
	
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id ) {
		User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당유저는 없습니다."));
		return user;
	}
	
	
	
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getRole());
		System.out.println(user.getCreateDate());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원 가입이 완료 되었습니다.";
		
	}
}
