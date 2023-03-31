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

import com.ctor.dto.AlarmDTO;
import com.ctor.dto.BlindDTO;
import com.ctor.dto.BoardDTO;
import com.ctor.dto.JobGroupDTO;
import com.ctor.dto.MemberDTO;
import com.ctor.dto.SkillDTO;
import com.ctor.service.AlarmService;
import com.ctor.service.ApiService;
import com.ctor.service.BlindService;
import com.ctor.service.BoardService;
import com.ctor.service.JobGroupService;
import com.ctor.service.KakaoLoginService;
import com.ctor.service.SkillService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CtorController {

	private final BoardService boardService;
	private final SkillService skillService;
	private final ApiService apiService;
	private final JobGroupService jobGroupService;
	private final KakaoLoginService kakaoLoginService;
	private final BlindService blindService;
	private final AlarmService alarmService;
	
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
	public void documentation(Model model) {
		List<JobGroupDTO> jobList = jobGroupService.getList();
		List<SkillDTO> skillList = skillService.getList();
		model.addAttribute("jobs",jobList);
		model.addAttribute("skillList",skillList);
	}
	@GetMapping("checkedAlarm")
	public String checkedAlarm(AlarmDTO dto) {
		
		alarmService.checkedAlarm(dto.getNum());
		return "redirect:"+dto.getUrl();
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
		//멘토 여부 확인 후 있으면 멘토 이름을 dto에 세팅
		for (BoardDTO board : dto) {
			  if (board.getMentorEmail() != null) {
			    try {
			      board.setMentorName(kakaoLoginService.findByEmail(board.getMentorEmail()).getName());
			    } catch (Exception e) {
			      // 예외 처리
			    }
			  }
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
	@GetMapping("admin")
	public void admin(Model model) {
		List<MemberDTO> memberList = kakaoLoginService.findAll();
		List<BoardDTO> boardList =  boardService.findAllBoards();
		List<BlindDTO> blindList = blindService.findAll();
		
		model.addAttribute("memberList",memberList);
		model.addAttribute("boardList",boardList);
		model.addAttribute("blindList",blindList);
	}
	
	@GetMapping("/changelog")
	public void changelog(Model model) {
		List<Map<String, String>> committer = apiService.apicall();
		model.addAttribute("list",committer);
	}
	
	
}
