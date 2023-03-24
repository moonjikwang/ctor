package com.ctor.service;

import org.springframework.stereotype.Service;

import com.ctor.dto.SkillLevelDTO;
import com.ctor.entity.SkillLevel;
import com.ctor.repository.SkillLevelRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SkillLevelServiceimpl implements SkillLevelService{

	private final SkillLevelRepository skillLevelRepository;
	
	@Override
	public SkillLevelDTO getByEmail(String email) {
		SkillLevel sl = skillLevelRepository.getByEmail(email);
		if(sl!=null) {
		return entityToDTO(sl);
		}else {
			return null;
		}
	}

	@Override
	public void register(SkillLevelDTO dto) {
		SkillLevel result = dtoToEntity(dto);
		System.out.println(result);
		skillLevelRepository.save(result);
		
	}

}
