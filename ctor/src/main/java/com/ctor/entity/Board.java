package com.ctor.entity;

import java.util.Date;

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
@ToString
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardno;	//글번호
	
	private String title;	//글제목
	private String text;	//글내용
	
	private Date closingDate;	//모집 마감일
	private Date projStartDate;	//프로젝트 시작일
	private Date projEndDate;	//프로젝트 마감일
	private String duration;	//프로젝트 기간 (미정, 1개월, 3개월, 6개월, 장기)
	
	private int groupMember;	//모집인원수
	private int viewCount;		//조회수
	private int replyCount;		//댓글수
	private String[] position;	//직군
	private String[] techStack;	//기술스택
	
	private boolean hasTutor;	//멘토링 있는지 여부
	
	
	
	//멤버 ID
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
