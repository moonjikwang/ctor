package com.ctor.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ctor.dto.BoardCommentsDTO;
import com.ctor.dto.BoardDTO;
import com.ctor.dto.JobGroupDTO;
import com.ctor.dto.SkillDTO;
import com.ctor.service.JobGroupService;
import com.ctor.service.SkillService;
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
		redirectAttributes.addAttribute("boardno",bno);
		return "redirect:boardRead";
	}
    
	@GetMapping("board/NewFile")
	public String newFile(Model model) {
		model.addAttribute("result", service.findAllBoards());

		return "board/NewFile";
	}
	
	@GetMapping("board/board")
	public String board(Model model) {
		model.addAttribute("result", service.findAllBoards());
				
		return "board/board";
	}
	
	
	
	

}