package com.ctor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctor.dto.BoardDTO;
import com.ctor.dto.JobGroupDTO;
import com.ctor.dto.SkillDTO;
import com.ctor.service.ApiService;
import com.ctor.service.BoardService;
import com.ctor.service.JobGroupService;
import com.ctor.service.SkillService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CtorController {

	private final BoardService boardService;
	private final SkillService skillService;
	private final ApiService apiService;
	private final JobGroupService jobGroupService;
	
	@GetMapping("/")
	public String home() {
		return "redirect:index";
	}
	
	@GetMapping("about")
	public void about(Model model) {
		int dtoSize = boardService.findAllBoards().size();
		model.addAttribute("dto",dtoSize);
	}
	@GetMapping("documentation")
	public void documentation() {
		
	}
	@GetMapping("index")
	public void index(Model model) {
		
		List<SkillDTO> skillList = skillService.getList();
		List<BoardDTO> dto = boardService.findAllBoards();
		List<JobGroupDTO> jobList = jobGroupService.getList();
		
		Map<String, String> skillMap = new HashMap<>();
		for(SkillDTO skill : skillList) {
			skillMap.put(skill.getSkill(), skill.getColor());
		}
		
		
		model.addAttribute("dto",dto);
		model.addAttribute("skillMap",skillMap);
		model.addAttribute("skillList",skillList);
		model.addAttribute("positionList",jobList);
		
		
	}
	@PostMapping("index")
	public String skillindex(@RequestParam(value = "skillChk") List<String> selectedSkills,RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("checkedList",selectedSkills);
		
		return "redirect:index";
	}
	
	@GetMapping("/changelog")
	public void changelog(Model model) {
		List<Map<String, String>> committer = apiService.apicall();
		model.addAttribute("list",committer);
	}
	
	
}
