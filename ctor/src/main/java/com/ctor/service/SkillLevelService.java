package com.ctor.service;

import com.ctor.dto.SkillLevelDTO;
import com.ctor.entity.Member;
import com.ctor.entity.SkillLevel;

public interface SkillLevelService {

	void register(SkillLevelDTO dto);
	SkillLevelDTO getByEmail(String email);
	
	default SkillLevelDTO entityToDTO(SkillLevel entity) {
		return SkillLevelDTO.builder()
				.no(entity.getNo())
		.java(entity.getJava())
		.htmlcss(entity.getHtmlcss())
		.javascript(entity.getJavascript())
		.jquery(entity.getJquery())
		.jsp(entity.getJsp())
		.mysql(entity.getMysql())
		.springboot(entity.getSpringboot())
		.build();
	}
	
	default SkillLevel dtoToEntity(SkillLevelDTO dto) {
		return SkillLevel.builder()
				.no(dto.getNo())
				.java(dto.getJava())
				.htmlcss(dto.getHtmlcss())
				.javascript(dto.getJavascript())
				.jquery(dto.getJquery())
				.jsp(dto.getJsp())
				.mysql(dto.getMysql())
				.springboot(dto.getSpringboot())
				.member(Member.builder().email(dto.getEmail()).build())
				.build();
	}
}
