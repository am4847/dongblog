package com.dong.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



//ORM -> java 오브젝트를 테이블로 만들어주는 것
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // user 클래스가 mysql에 테이블에 자동으로 생성된다.
//@DynamicInsert insert시에 null인 value를 제외해준다.
public class User {
 
		@Id //Primary key
		@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
		private int id; // 시퀀스, auto_increment
		
		@Column(nullable = false, length = 100, unique = true)
		private String username;
		
		@Column(nullable = false, length = 100)// 123456 -> 해쉬 (비밀번호 암호화)
		private String password;
		
		@Column(nullable = false, length = 50)
		private String email;
		
		//@ColumnDefault("user")
		@Enumerated(EnumType.STRING)
		private RoleType role; // Enum을 쓰는게 좋다. -> admin, userm manager.... enum으로 상수를 정의하고 정의 된 권한 안에서 정리(오타, 잘못된 처리 예방) 도메인을 정할 수 있다.
		
		@CreationTimestamp
		private Timestamp createDate;
		
		
}
