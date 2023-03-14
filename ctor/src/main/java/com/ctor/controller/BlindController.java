package com.ctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlindController {

	@GetMapping("blind")
	public void blind() {
	}
	
	@GetMapping("blindWrite")
	public void blindWrite() {
		
	}
}
