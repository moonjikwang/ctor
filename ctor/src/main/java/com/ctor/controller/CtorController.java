package com.ctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctor.dto.MemberDTO;

@Controller
public class CtorController {

	@GetMapping("index")
	public void index() {
	}
	@PostMapping("/register")
	public String register(MemberDTO dto) {
		System.out.println(dto.toString());
		return "index";
		
	}
}
