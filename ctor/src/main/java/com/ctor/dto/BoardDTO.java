package com.ctor.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.json.simple.JSONObject;

import com.ctor.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

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
	private String position;	//직군
	private String techStack;	//기술스택
	private boolean hasTutor;	//멘토링 있는지 여부
	
	private String memEmail;	//멤버 대신 멤버 이메일
	
}
