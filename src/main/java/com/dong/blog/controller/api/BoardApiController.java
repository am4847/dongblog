package com.dong.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dong.blog.config.auth.PrincipalDetail;
import com.dong.blog.dto.ReplySaveRequestDto;
import com.dong.blog.dto.ResponseDto;
import com.dong.blog.model.Board;
import com.dong.blog.model.Reply;
import com.dong.blog.model.RoleType;
import com.dong.blog.model.User;
import com.dong.blog.repository.UserRepository;
import com.dong.blog.service.BoardService;
import com.dong.blog.service.UserService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println(board.toString()+"\t"+principal.getUser().toString());
		boardService.글쓰기(board,principal.getUser());
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@DeleteMapping("/api/board/{no}")
	public ResponseDto<Integer> deleteById(@PathVariable int no) {
		System.out.println("================BoardApiController::deleteById");
		boardService.삭제하기(no);
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PutMapping("/api/board")
	public ResponseDto<Integer> update(@RequestBody Board board) {
		boardService.수정하기(board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	@PostMapping("/api/board/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto  ) {
		/* int boardId = 16; */
			boardService.댓글쓰기(replySaveRequestDto);
			System.out.println(replySaveRequestDto.getBoardNo()+"\t"+replySaveRequestDto.getUserNo()+"\t"+replySaveRequestDto.getContent());
			System.out.println("==============BoardApiController::replySave::in");

			
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	@PostMapping("/api/board/reply/detail")
	public Reply replyFindById(@RequestBody Reply requestreply, Model model ) {
		Reply reply = boardService.댓글보기(requestreply);
		reply.setUser(null);
		
		reply.setBoard(Board.builder().no(reply.getBoard().getNo()).build());
		return reply;
	}
	@DeleteMapping("/api/board/{boardNo}/reply/{replyNo}")
	public ResponseDto<Integer> replyDeleteById(@PathVariable int replyNo) {
		System.out.println("================BoardApiController::deleteById");
		boardService.댓글삭제하기(replyNo);
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PutMapping("/api/board/reply/update")
	public ResponseDto<Integer> replyUpdate(@RequestBody Reply reply) {
		System.out.println("================BoardApiController::replyUpdate");
		boardService.댓글수정하기(reply);
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
