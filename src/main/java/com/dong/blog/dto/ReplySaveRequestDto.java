package com.dong.blog.dto;

import com.dong.blog.model.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
	private int userNo;
	private int boardNo;
	private String content;
}
