package com.ctor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctor.dto.MemberDTO;
import com.ctor.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final KakaoLoginService kakaoLoginService;
	
	@PostMapping("/login")
	public String memberLogin(String email,String password,HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		PrintWriter printWriter = null;
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
		MemberDTO member = kakaoLoginService.login(email, password);
		if(member != null) {
			session.setAttribute("userInfo", member);
			}else {
				try {
					printWriter = res.getWriter();
		            printWriter.println("<script type='text/javascript'>"
		                    + "alert('로그인에 실패했습니다.');"
		                    + "history.back();"
		                    +"</script>");

		            printWriter.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
		                if(printWriter != null) { printWriter.close(); }
		            
		        }
			}
		return "redirect:/index";
	}
}
