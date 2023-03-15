package com.ctor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ctor.dto.BlindDTO;

@Controller
public class BlindController {

	@GetMapping("blind")
	public void blind() {
	}
	
	@GetMapping("blindWrite")
	public void blindWrite() {
	}
	
	@PostMapping("blindWrite")
	public String postImage(BlindDTO dto) {
		System.out.println("=========입력받은값 :"+dto);
		return "redirect:blind";
	}
}
