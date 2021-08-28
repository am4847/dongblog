package com.dong.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dong.blog.dto.ReplySaveRequestDto;
import com.dong.blog.model.Board;
import com.dong.blog.model.Reply;
import com.dong.blog.model.User;
import com.dong.blog.repository.BoardRepository;
import com.dong.blog.repository.ReplyRepository;
import com.dong.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌 . loc를 해준다.
@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	

	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
		
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int no) {
		System.out.println("==============BoardService::글상세보기::in");
		return boardRepository.findById(no).orElseThrow(()->new IllegalArgumentException("글 상세보기 실패"));
		
	}

	@Transactional
	public void 삭제하기(int no) {
		boardRepository.deleteById(no);
		
	}
	@Transactional
	public void 수정하기(Board requestBoard) {
		Board board = boardRepository.findById(requestBoard.getNo()).orElseThrow(()->new IllegalArgumentException("수정글찾기 실패"));
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		board.setCategory(requestBoard.getCategory()); 
	}

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto  ) {
		
		
		replyRepository.CustomSave(replySaveRequestDto.getUserNo(),replySaveRequestDto.getBoardNo(),replySaveRequestDto.getContent());
	}

	@Transactional
	public void 댓글삭제하기(int no) {
		replyRepository.deleteById(no);
		
	}
	@Transactional
	public void 댓글수정하기(Reply requestReply) {
		System.out.println("============BoardService::댓글수정하기");
		System.out.println(requestReply.getNo()+"\t"+requestReply.getContent());
		Reply reply = replyRepository.findById(requestReply.getNo()).orElseThrow(()->new IllegalArgumentException("수정댓글찾기 실패"));
		
		reply.setContent(requestReply.getContent());

		
		
	}
	@Transactional(readOnly = true)
	public Reply 댓글보기(Reply reply) {
		

		return replyRepository.findById(reply.getNo()).orElseThrow(()->new IllegalArgumentException("댓글찾기 실패")) ;
		
		
	}

}

//@Transactional(readOnly = true)
//public User 로그인(User user) {
//	System.out.println("=======UserService::로그인::in");
//	User user1 = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//	User user2 = userRepository.login(user.getUsername(),user.getPassword());
//	if(user1.equals(user2))System.out.println(user1 +"\t"+ user2);
//	System.out.println("=======UserService::로그인::out");
//	return user1;
//	return userRepository.login(user.getUsername(),user.getPassword());
//}
