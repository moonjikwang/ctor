package com.ctor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctor.dto.SkillDTO;
import com.ctor.entity.Skill;
import com.ctor.repository.SkillRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SkillServiceimpl implements SkillService{

	@Autowired
	private final SkillRepository skillRepository;
	
	@Override
	public List<SkillDTO> getList() {
		List<Skill> list = skillRepository.findAll();
		return list.stream().map(skill -> entityToDTO(skill)).collect(Collectors.toList());
	}

}
