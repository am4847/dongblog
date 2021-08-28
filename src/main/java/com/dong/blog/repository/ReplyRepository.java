package com.dong.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dong.blog.dto.ReplySaveRequestDto;
import com.dong.blog.model.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Modifying
	@Query(value = "insert into reply(userNo, boardNo, content, createDate) VALUES(?, ?, ?, now() ) ",nativeQuery = true)
	int CustomSave(	 int userNo, int boardNo, String content);
}
