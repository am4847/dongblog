package com.dong.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dong.blog.config.auth.PrincipalDetail;
import com.dong.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String index(Model model, @PageableDefault(size=3,sort = "no", direction = Sort.Direction.DESC) Pageable pageable ) {
		 //@AuthenticationPrincipal PrincipalDetail principal
		//System.out.println(principal.getUsername()+"\t"+principal.getPassword());
		model.addAttribute("boards",boardService.글목록(pageable));
		//boardService.글목록().forEach(System.out::println);
		
		return "index";
	}
	
	@GetMapping("/board/{no}")
	public String findById(@PathVariable int no, Model model) {
		model.addAttribute("board", boardService.글상세보기(no));
		System.out.println("===========BoardController::findById::in");
		return "board/detail";
		
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{no}/updateForm")
	public String updateForm(@PathVariable int no, Model model) {
		System.out.println("***********************BoardController::updateForm");
		model.addAttribute("board",boardService.글상세보기(no));
		return "board/updateForm";
	}
	
	
}
