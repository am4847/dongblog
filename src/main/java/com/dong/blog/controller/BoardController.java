package com.dong.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dong.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	
	
	@GetMapping("/")
	public String index( ) {
		 //@AuthenticationPrincipal PrincipalDetail principal
		//System.out.println(principal.getUsername()+"\t"+principal.getPassword());
		return "index";
	}
	
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
