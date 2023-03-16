package com.ctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ctor.dto.JobGroupDTO;
import com.ctor.dto.SkillDTO;
import com.ctor.service.JobGroupService;
import com.ctor.service.SkillService;

@Controller
public class BoardController {

	@Autowired
	SkillService skillService;
	@Autowired
	JobGroupService jobGroupService;
	@GetMapping("boardWrite")
	public void boardWrite(Model model) {
		List<SkillDTO> list = skillService.getList();
		List<JobGroupDTO> jobList = jobGroupService.getList();
		model.addAttribute("skill",list);
		model.addAttribute("job",jobList);
	}
}
