package com.ctor.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

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
	public void index(Model model, HttpServletRequest request) {
		System.out.println("인덱스 get실행");
		List<BoardDTO> dto = boardService.findAllBoards();
		List<SkillDTO> skillList = skillService.getList();
		
		Map<String, String> skillMap = new HashMap<>();
		for(SkillDTO skill : skillList) {
			skillMap.put(skill.getSkill(), skill.getColor());
		}
		
		HttpSession session = request.getSession();
		
		System.out.println("session.getAttribute('chkdSkill')");
		System.out.println(session.getAttribute("chkdSkill"));
		
		//선택한 스킬이 없으면 모든 스킬 반환
		if(session.getAttribute("chkdSkill") == null) {
			System.out.println("선택된 스킬 없음");
			List<String> allSkills = new ArrayList();
			for (int i = 0; i < skillList.size(); i++) {
				allSkills.add(skillList.get(i).getSkill()); 
				System.out.println(allSkills.get(i));
			}
			session.setAttribute("chkdSkill", allSkills);
		}else {
			List<String> skillChk = new ArrayList<>();
			for(int i = 0; i < ((List<String>)session.getAttribute("chkdSkill")).size(); i++) {
				skillChk.add(((List<String>)session.getAttribute("chkdSkill")).get(i));
			}
			session.setAttribute("chkdSkill", skillChk);
			System.out.println("skillChk");
			System.out.println(skillChk);
		}
		
		
		model.addAttribute("dto",dto);
		model.addAttribute("skillMap",skillMap);
		model.addAttribute("skillList",skillList);
		
	}
	@GetMapping("/changelog")
	public void changelog(Model model) {
		List<Map<String, String>> committer = apiService.apicall();
		model.addAttribute("list",committer);
	}
	
	/**
	 * 
	 * 이하 내용을 스크립트에서 처리하도록 하고 삭제예정
	 */
	@PostMapping("/tab")
	public String tab(@RequestParam(value = "skillChk", required = false) List<String> selectedSkills, HttpServletRequest request) {
		System.out.println("post 실행");
		List<SkillDTO> skillList = skillService.getList();
		List<String> chkdSkill = selectedSkills;
		//선택한 스킬이 없으면 모든 스킬 반환
		if(selectedSkills == null || selectedSkills.size() == 0) {
			List<String> allSkills = new ArrayList();
			for (int i = 0; i < skillList.size(); i++) {
				allSkills.add(skillList.get(i).getSkill()); 
				System.out.println(allSkills.get(i));
			}
			chkdSkill = allSkills;
		}
		HttpSession session = request.getSession();
		session.setAttribute("chkdSkill", chkdSkill);
		
		System.out.println("selectedSkills : "+selectedSkills);
		System.out.println("chkdSkill : "+chkdSkill);
		System.out.println(session.getAttribute("chkdSkill"));
		return "redirect:/index";

		
	}
}
