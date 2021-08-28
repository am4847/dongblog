package com.dong.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dong.blog.model.User;


// DAO
// 자동으로 bean 등록되기 때문에 @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserId(String UserId);

	Optional<User> findByUserName(String userName);
}
////JPA Naming 전략
//	User findByUsernameAndPassword(String username, String password);
//	
//	@Query(value = "SELECT * FROM USER WHERE username = ? AND password = ? ",nativeQuery = true)
//	User login(String username, String password);