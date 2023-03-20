package com.ctor.dto;




import java.time.LocalDateTime;
import java.util.Date;

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
	private LocalDateTime regDate;	//작성일
	private LocalDateTime modDate;	//수정일
	
	private String title;	//글제목
	private String text;	//글내용
	private String category;//카테고리
	
	private String closingDate;	//모집 마감일
	private String duration;	//프로젝트 기간 (미정, 1개월, 3개월, 6개월, 장기)
	
	private String chatLink;
	private int groupMember;	//모집인원수
	private int viewCount;		//조회수
	private String position;	//직군
	private String techStack;	//기술스택
	private boolean hasTutor;	//멘토링 있는지 여부
	private boolean closed;		//마감여부
	
	private int replyCount;		//댓글수(리플 테이블에서 조회)
	private int memberCount;	//모집된 참여자 수(신청 테이블에서 조회)
	private String memEmail;	//멤버 PK(이하 멤버테이블에서 조회)
	private String nickName;	//멤버 닉네임
	private String profileImg;	//멤버 프로필사진
	
}
