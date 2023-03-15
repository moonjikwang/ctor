package com.ctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

	@GetMapping("boardWrite")
	public void boardWrite() {
	}
}
