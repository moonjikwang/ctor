package com.ctor.service;

import java.util.HashMap;

import com.ctor.dto.MemberDTO;
import com.ctor.entity.Member;

public interface KakaoLoginService {

	String getAccessToken(String authorize_code) throws Throwable;
	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable;
	
	MemberDTO findByEmail(String email);
	
	String register(MemberDTO dto);
	
	default MemberDTO entityToDto(Member member) {
		MemberDTO dto = MemberDTO.builder()
		.email(member.getEmail())
		.name(member.getName())
		.grade(member.getGrade())
		.subject(member.getSubject())
		.nickName(member.getNickName())
		.introduce(member.getIntroduce())
		.profileImg(member.getProfileImg())
		.modDate(member.getModDate())
		.regDate(member.getRegDate())
		.build();
		return dto;
	}
	default Member dtoToEntity(MemberDTO dto) {
		Member entity = Member.builder()
		.email(dto.getEmail())
		.name(dto.getName())
		.grade(dto.getGrade())
		.nickName(dto.getNickName())
		.introduce(dto.getIntroduce())
		.subject(dto.getSubject())
		.profileImg(dto.getProfileImg())
		.build();
		return entity;
	}
	
}