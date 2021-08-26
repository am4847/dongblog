package com.dong.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dong.blog.model.Board;
import com.dong.blog.model.Reply;
import com.dong.blog.model.User;
import com.dong.blog.repository.BoardRepository;
import com.dong.blog.repository.ReplyRepository;

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
	public Board 글상세보기(int id) {
		System.out.println("==============BoardService::글상세보기::in");
		return boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("글 상세보기 실패"));
		
	}

	@Transactional
	public void 삭제하기(int id) {
		boardRepository.deleteById(id);
		
	}
	@Transactional
	public void 수정하기(Board requestBoard) {
		Board board = boardRepository.findById(requestBoard.getId()).orElseThrow(()->new IllegalArgumentException("수정글찾기 실패"));
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		board.setCategory(requestBoard.getCategory()); 
	}

	@Transactional
	public void 댓글쓰기(int boardId, Reply reply , User user ) {
		reply.setBoard(boardRepository.getById(boardId));
		reply.setUser(user);
		replyRepository.save(reply);
		
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
