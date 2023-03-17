package com.ctor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctor.dto.JobGroupDTO;
import com.ctor.entity.JobGroup;
import com.ctor.repository.JobGroupRepository;
import com.ctor.repository.SkillRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Service
@RequiredArgsConstructor
@Log4j2
public class JobGroupServiceimpl implements JobGroupService{

	@Autowired
	private final JobGroupRepository jobGroupRepository;
	
	@Override
	public List<JobGroupDTO> getList() {
		List<JobGroup> list = jobGroupRepository.findAll();
		return list.stream().map(job->entityToDTO(job)).collect(Collectors.toList());
	}

}
