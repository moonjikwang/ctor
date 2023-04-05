package com.ctor.controller;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctor.dto.AlarmDTO;
import com.ctor.dto.BlindDTO;
import com.ctor.dto.BoardCommentsDTO;
import com.ctor.dto.BoardDTO;
import com.ctor.dto.JobGroupDTO;
import com.ctor.dto.ParticipationDTO;
import com.ctor.dto.SkillDTO;
import com.ctor.entity.Member;
import com.ctor.service.JobGroupService;
import com.ctor.service.KakaoLoginService;
import com.ctor.service.ParticipationService;
import com.ctor.service.PushService;
import com.ctor.service.SkillService;
import com.ctor.service.AlarmService;
import com.ctor.service.BoardCommentsService;
import com.ctor.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	@Autowired
	SkillService skillService;
	@Autowired
	JobGroupService jobGroupService;
	@Autowired
	BoardCommentsService boardCommentsService;
	@Autowired
	ParticipationService participationService;
	@Autowired
	KakaoLoginService kakaoLoginService;
	@Autowired
	PushService pushService;
	@Autowired
	AlarmService alarmService;
  
	@GetMapping("boardWrite")
	public void boardWrite(Model model) {
		List<SkillDTO> list = skillService.getList();
		List<JobGroupDTO> jobList = jobGroupService.getList();
		model.addAttribute("skill",list);
		model.addAttribute("job",jobList);
  }
	@PostMapping("boardWrite")
	public String postImage(BoardDTO dto,RedirectAttributes redirectAttributes) {
		Long bno = service.write(dto);
		ParticipationDTO pcDTO = ParticipationDTO.builder().pBno(bno).pMemEmail(dto.getMemEmail()).build();
		participationService.participate(pcDTO);
		redirectAttributes.addAttribute("boardno",bno);
		return "redirect:boardRead";
	}
	@PostMapping("boardModify")
	public String boardModify(BoardDTO dto,RedirectAttributes redirectAttributes) {
		Long bno = service.write(dto);
		redirectAttributes.addAttribute("boardno",bno);
		return "redirect:boardRead";
	}
	@GetMapping({"boardRead","boardModify"})
	public void boardRead(long boardno, Model model) {
		BoardDTO dto = service.findByBno(boardno);
		List<BoardCommentsDTO> comment = boardCommentsService.findByBno(boardno);
		service.viewCount(boardno);
		List<SkillDTO> list = skillService.getList();
		List<JobGroupDTO> jobList = jobGroupService.getList();
		List<ParticipationDTO> partiList = participationService.findByBno(boardno);
		List<String> partEmailList = new ArrayList<String>();
		partiList.forEach(parti -> partEmailList.add(parti.getPMemEmail()));
		
		//멘토 여부 확인 후 있으면 멘토 이름을 dto에 세팅
		if(dto.getMentorEmail()!=null) {
			dto.setMentorName(kakaoLoginService.findByEmail(dto.getMentorEmail()).getName() );
		}
		
		model.addAttribute("partyMember",partiList);
		model.addAttribute("partyList",partEmailList);
		model.addAttribute("skill",list);
		model.addAttribute("job",jobList);
		model.addAttribute("dto",dto);
		model.addAttribute("comments",comment);
	}
	@GetMapping("boardDelete")
	public String boardDelete(long boardno) {
		service.delete(boardno);
		return "redirect:/index";
	}
	
	@PostMapping("boardCommentWrite")
	public String boardCommentWrite(BoardCommentsDTO dto,RedirectAttributes redirectAttributes) {
		boardCommentsService.write(dto);
		Long bno = dto.getBCommentBno();
		
		/*========== 알림 전송========================================================== */
		BoardDTO boarddto = service.findByBno(bno);
		String email = boarddto.getMemEmail();
		String[] target = {email}; 
		String title = "새 댓글 알림";
		String content = boarddto.getName()+"님 프로젝트/스터디 모집 게시글에 새로운 댓글이 작성되었습니다.";
		String url = "https://tomcat.jikwang.net/ctor/boardRead?boardno="+bno;
		pushService.push(target, title, content,url);
		alarmService.addAlarm(AlarmDTO.builder().email(boarddto.getMemEmail()).isChecked(false).title(title).text(content).url(url).build());
		/*========== 알림 전송=========================================================== */
		
		redirectAttributes.addAttribute("boardno",bno);
		return "redirect:boardRead";
	}
	@GetMapping("boardCommentRemove")
	public String blindCommentRemove(Long bCommentNo,Long boardno,RedirectAttributes redirectAttributes) {
		boardCommentsService.delete(bCommentNo);
		redirectAttributes.addAttribute("boardno",boardno);
		return "redirect:boardRead";
	}
    

	
	
	
	

}