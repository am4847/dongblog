package com.dong.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// 
// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

		@GetMapping("/http/get")
		public String getTest(Member m) {
			return "get 요청 id:"+m.getId()+","+m.getUsername();
			
		}
		
		@PostMapping("/http/post")
		public String postTest(@RequestBody Member m)  {
			return "post 요청 id:"+m.getId()+","+m.getUsername();
		}
		@PutMapping("/http/put")
		public String putTest() {
			return "put 요청";
		}
		@DeleteMapping("/http/delete")
		public String deleteTest() {
			return "delete 요청";
		}
		
		
}
