package com.dong.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
		@Id //Primary key
		@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
		private int no; // 시퀀스, auto_increment
		
		@Column(nullable = false, length = 200)
		private String content;
		
		@ManyToOne
		@JoinColumn(name="boardNo")
		private Board board;
		
		@ManyToOne
		@JoinColumn(name = "userNo")
		private User user;
		
		@CreationTimestamp
		private Timestamp createDate;
}
