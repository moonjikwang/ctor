package com.ctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctor.dto.BoardDTO;
import com.ctor.dto.MemberDTO;
import com.ctor.dto.SkillDTO;
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
	@GetMapping("/")
	public String home() {
		return "redirect:/index";
	}
	@GetMapping("/index")
	public void index(Model model) {
		List<BoardDTO> dto = boardService.findAllBoards();
		List<SkillDTO> skillList = skillService.getList();
		
		Map<String, String> skillMap = new HashMap<>();
		for(SkillDTO skill : skillList) {
			skillMap.put(skill.getSkill(), skill.getColor());
		}
		System.out.println(skillMap);
		model.addAttribute("dto",dto);
		model.addAttribute("skillMap",skillMap);
	}
	@PostMapping("/register")
	public String register(MemberDTO dto,HttpServletRequest request) {
		System.out.println(dto.toString());
		String email = kakaoLoginService.register(dto);
		if(email!= null) {
			System.out.println(email + "회원 가입완료");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", dto);
		}else {
			System.out.println("회원가입 오류발생");
		}
		return "index";
		
	}
}
