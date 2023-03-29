package com.ctor.service;

import java.util.HashMap;
import java.util.List;

import com.ctor.dto.MemberDTO;
import com.ctor.entity.Member;

public interface KakaoLoginService {

	public String getAccessToken(String authorize_code) throws Throwable;
	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable;
	public MemberDTO login(String email,String password);
	public MemberDTO findByEmail(String email);
	public List<MemberDTO> findAll();
	public String register(MemberDTO dto);
	public void modify(MemberDTO dto);
	public void addTeacher(String email);
	public void deleteById(String email);
	
	default MemberDTO entityToDto(Member member) {
		MemberDTO dto = MemberDTO.builder()
		.email(member.getEmail())
		.name(member.getName())
		.grade(member.getGrade())
		.subject(member.getSubject())
		.password(member.getPassword())
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
		.password(dto.getPassword())
		.subject(dto.getSubject())
		.profileImg(dto.getProfileImg())
		.build();
		return entity;
	}
	
}