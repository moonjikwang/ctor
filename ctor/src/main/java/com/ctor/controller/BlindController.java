package com.ctor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctor.dto.BlindDTO;

@Controller
public class BlindController {

	@GetMapping("blind")
	public void blind() {
	}
	
	@GetMapping("blindWrite")
	public void blindWrite() {
	}
	@GetMapping("blindRead")
	public void blindRead() {
		
	}
	
	@PostMapping("blindWrite")
	public String postImage(BlindDTO dto) {
		System.out.println("=========입력받은값 :"+dto);
		return "redirect:blind";
	}
}
