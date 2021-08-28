package com.dong.blog.model;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int no;
		
		@Column(nullable = false, length = 100)
		private String title;
		
		@Lob //대용량 데이터를 쓸 때 사용
		private String content;
		
		@Enumerated(EnumType.STRING)
		private Category category; 
		
		private int count;
		
		@ManyToOne(fetch =  FetchType.EAGER) // Many = board, user =one 
		@JoinColumn(name = "userNo")
		private User user;
		
		
		@OneToMany(mappedBy = "board",fetch =  FetchType.EAGER, cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties({"board"})
		@OrderBy("no desc")
		private List<Reply> replys;
		
		
		
		@CreationTimestamp
		private Timestamp createDate;
}
