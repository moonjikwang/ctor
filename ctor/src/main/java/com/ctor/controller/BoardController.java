package com.ctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ctor.service.BoardService;
import com.ctor.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	
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
