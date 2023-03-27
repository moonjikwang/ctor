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
import com.ctor.dto.SkillDTO;
import com.ctor.service.ApiService;
import com.ctor.service.BoardService;
import com.ctor.service.SkillService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CtorController {

	private final BoardService boardService;
	private final SkillService skillService;
	private final ApiService apiService;
	@GetMapping("/")
	public String home() {
		return "redirect:index";
	}
	
	@GetMapping("about")
	public void about() {
		
	}
	
	@GetMapping("index")
	public void index(Model model,@RequestParam(value = "checkedList", required = false) List<String> selectedSkills) {
		
		List<SkillDTO> skillList = skillService.getList();
		List<BoardDTO> dto = new ArrayList<>();
		Map<String, String> skillMap = new HashMap<>();
		for(SkillDTO skill : skillList) {
			skillMap.put(skill.getSkill(), skill.getColor());
		}
		
		List<String> chkdSkill = selectedSkills;
		
		if(chkdSkill!=null) {
			Set<BoardDTO> tempSet = new HashSet<>();
			for (String skill : chkdSkill) {
			    boardService.findByTech(skill).forEach(tempDTO ->{
			        tempSet.add(tempDTO);
			    });
			}
			List<BoardDTO> tempList = new ArrayList<>(tempSet);
			dto = tempList;
		}else {
		dto = boardService.findAllBoards();
		}
		model.addAttribute("chkdSkill",chkdSkill);
		model.addAttribute("dto",dto);
		model.addAttribute("skillMap",skillMap);
		model.addAttribute("skillList",skillList);
		
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
