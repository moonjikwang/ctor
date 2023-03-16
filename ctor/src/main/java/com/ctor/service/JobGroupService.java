package com.ctor.service;

import java.util.List;

import com.ctor.dto.JobGroupDTO;
import com.ctor.entity.JobGroup;

public interface JobGroupService {

	List<JobGroupDTO> getList();
	
	default JobGroupDTO entityToDTO(JobGroup jobGroup) {
		return JobGroupDTO.builder().name(jobGroup.getName()).build();
	}
}
