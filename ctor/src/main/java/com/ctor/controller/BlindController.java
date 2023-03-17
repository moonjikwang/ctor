package com.ctor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.service.BlindService;

@Controller
public class BlindController {

	@Autowired
	BlindService blindService;
	
	@GetMapping("blind")
	public void blind() {
	}
	
	@GetMapping("blindWrite")
	public void blindWrite() {
	}
	@GetMapping({"blindRead","blindmodify"})
	public void read(long bno, @ModelAttribute("requestDTO") BlindPageRequestDTO pageRequestDTO, Model model) {
		/* BlindDTO dto = blindService.read(bno); */
		/* model.addAttribute("dto",dto); */
		model.addAttribute("requestDTO",pageRequestDTO);
	}
	
	@PostMapping("blindWrite")
	public String postImage(BlindDTO dto,RedirectAttributes redirectAttributes) {
		System.out.println("=========입력받은값 :"+dto);
		Long blind_no = blindService.register(dto);
		redirectAttributes.addAttribute("blind_no",blind_no);
		return "redirect:blindRead";
	}
}
