package com.ctor.entity;




import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardno;	//글번호
	
	private String title;	//글제목
	private String text;	//글내용
	private String category;	//카테고리
	
	private String closingDate;	//모집 마감일
	private String duration;	//프로젝트 기간 (미정, 1개월, 3개월, 6개월, 장기)
	
	private String chatLink; //카카오 오픈챗 링크
	private int groupMember;	//모집인원수
	private int viewCount;		//조회수
	private String position;	//직군
	private String techStack;	//기술스택
	
	private boolean hasTutor;	//멘토링 있는지 여부
	private boolean closed;		//마감여부
	
	
	
	//멤버 ID
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
