package com.dong.blog.test;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dong.blog.config.auth.PrincipalDetail;



@RestController
public class Oauth2Test {
	
	
	@GetMapping("/test/oauth2test")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetail principalDetail) {
		System.out.println(principalDetail);
		return "user";
	}
}
