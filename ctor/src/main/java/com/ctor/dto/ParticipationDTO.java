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
public class ParticipationDTO {

	private Long pNo;	//신청서 PK
	private LocalDateTime pRegDate;	//작성일
	private LocalDateTime pModDate;	//수정일
	
	private Long pBno;	//신청한 프로젝트/스터디
	private String title;
	private String pMemEmail;	//멤버 PK(이하 멤버테이블에서 조회)
	private String pNickName;	//멤버 닉네임
	private String pProfileImg;	//멤버 프로필사진
}
