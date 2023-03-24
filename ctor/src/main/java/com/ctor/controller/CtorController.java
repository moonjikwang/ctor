package com.ctor.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctor.dto.BoardDTO;
import com.ctor.dto.MemberDTO;
import com.ctor.dto.SkillDTO;
import com.ctor.service.ApiService;
import com.ctor.service.BoardService;
import com.ctor.service.KakaoLoginService;
import com.ctor.service.SkillService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CtorController {

	private final KakaoLoginService kakaoLoginService;
	private final BoardService boardService;
	private final SkillService skillService;
	private final ApiService apiService;
	@GetMapping("/")
	public String home() {
		return "redirect:/index";
	}
	@GetMapping("/index")
	public void index(Model model, String[] skillChk) {
		
		List<BoardDTO> dto = boardService.findAllBoards();
		List<SkillDTO> skillList = skillService.getList();
		
		Map<String, String> skillMap = new HashMap<>();
		for(SkillDTO skill : skillList) {
			skillMap.put(skill.getSkill(), skill.getColor());
		}
		
		//선택한 스킬이 없으면 모든 스킬 반환
		if(skillChk == null || skillChk.length == 0) {
			String[] allSkills = new String[skillList.size()];
			for (int i = 0; i < allSkills.length; i++) {
				allSkills[i] = skillList.get(i).getSkill(); 
				System.out.println(allSkills[i]);
			}
			skillChk = allSkills;
		}
		model.addAttribute("dto",dto);
		model.addAttribute("skillMap",skillMap);
		model.addAttribute("skillList",skillList);
		model.addAttribute("ChkdSkill",skillChk);
		
		System.out.println("skillChk : "+Arrays.toString(skillChk));
	}
	@GetMapping("/changelog")
	public void changelog(Model model) {
		List<Map<String, String>> committer = apiService.apicall();
		model.addAttribute("list",committer);
	}
	

	@GetMapping("/index/tab")
	public void tab(Model model) {
		
	}
}
