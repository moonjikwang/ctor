package com.ctor.JobGroup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.entity.JobGroup;
import com.ctor.repository.JobGroupRepository;

@SpringBootTest
public class JobGroupTests {

	@Autowired
	private JobGroupRepository jobGroupRepository;
	
	@Test
	public void insertTest() {
		JobGroup job = JobGroup.builder().name("디자이너").build();
		jobGroupRepository.save(job);
	}
}
