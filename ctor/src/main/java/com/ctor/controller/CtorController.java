package com.ctor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctor.dto.MemberDTO;
import com.ctor.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CtorController {

	private final KakaoLoginService kakaoLoginService;
	
	@GetMapping("index")
	public void index() {
	}
	@PostMapping("/register")
	public String register(MemberDTO dto,HttpServletRequest request) {
		System.out.println(dto.toString());
		String email = kakaoLoginService.register(dto);
		if(email!= null) {
			System.out.println(email + "회원 가입완료");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", dto);
		}else {
			System.out.println("회원가입 오류발생");
		}
		return "index";
		
	}
}
