package com.ctor.dto;

import java.time.LocalDateTime;

import com.ctor.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentsDTO {

	private Long bCommentNo; // 댓글 글번호
	private LocalDateTime bCommentRegDate;	//작성일
	private LocalDateTime bCommentModDate;	//수정일
	
	private String bCommentText; // 댓글내용
	
	private Long bCommentBno;	//원글번호(보드PK)
	
	private String bCommentWriterEmail;	//멤버 PK(이하 멤버테이블에서 조회)
	private String bCommentNickName;	//멤버 닉네임
	private String bCommentProfileImg;	//멤버 프로필사진
}
