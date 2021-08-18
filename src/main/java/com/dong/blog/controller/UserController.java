package com.dong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//인증이 안된 사용자들이 출입할 수 있는 경로를  /auth/** 허용
//그냥 주소가 / 이면 index.jsp까지 허용
// static이하에 있는 /js/**, /css 등등 처음 화면을 잡아줄 화면이 필요할 링크는 공유
@Controller
public class UserController {
		
		@GetMapping("/auth/joinForm")
		public String joinForm() {
			return "user/joinForm";
		}
		
		@GetMapping("/auth/loginForm")
		public String loginForm() {
			return "user/loginForm";
		}
}
