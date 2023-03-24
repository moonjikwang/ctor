package com.ctor.service;

import java.util.List;

import com.ctor.dto.SkillDTO;
import com.ctor.entity.Skill;
import com.ctor.entity.SkillLevel;

public interface SkillService {

	List<SkillDTO> getList();
	
	default SkillDTO entityToDTO(Skill skill) {
		SkillDTO result = SkillDTO.builder().skill(skill.getSkill()).color(skill.getColor()).build();
		return result;
	}
}
