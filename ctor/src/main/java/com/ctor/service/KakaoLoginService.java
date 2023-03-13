package com.ctor.service;

import java.util.HashMap;

import com.ctor.dto.MemberDTO;
import com.ctor.entity.Member;

public interface KakaoLoginService {

	String getAccessToken(String authorize_code) throws Throwable;

	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable;
	
	MemberDTO findByEmail(String email);
	
	default MemberDTO entityToDto(Member member) {
		MemberDTO dto = MemberDTO.builder()
		.email(member.getEmail())
		.name(member.getName())
		.profileImg(member.getProfileImg())
		.modDate(member.getModDate())
		.regDate(member.getRegDate())
		.build();
		return dto;
	}
	
}