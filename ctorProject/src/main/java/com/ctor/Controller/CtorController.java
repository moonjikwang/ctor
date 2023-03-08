package com.ctor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CtorController {

	@GetMapping("index")
	public void index() {
	}
}
