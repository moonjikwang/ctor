package com.ctor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctor.dto.BoardDTO;
import com.ctor.dto.MemberDTO;
import com.ctor.dto.ParticipationDTO;
import com.ctor.service.BlindCommentsService;
import com.ctor.service.BlindService;
import com.ctor.service.BoardCommentsService;
import com.ctor.service.BoardService;
import com.ctor.service.KakaoLoginService;
import com.ctor.service.ParticipationService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final KakaoLoginService kakaoLoginService;
	private final ParticipationService participationService;
	private final BoardService boardService;
	private final BoardCommentsService BoardCommentsService;
	
	@GetMapping("/myPage")
	public void myPage(String email,Model model) {
		MemberDTO dto = kakaoLoginService.findByEmail(email);
		List<ParticipationDTO> boardList = participationService.findByEmail(email);
		int writeCount = boardService.findByEmail(email).size();
		int projectSize = participationService.findByEmail(email).size();
		int replyCount = BoardCommentsService.findByEmail(email).size();
		model.addAttribute("replyCount",replyCount);
		model.addAttribute("writeCount",writeCount);
		model.addAttribute("partCount",projectSize);
		model.addAttribute("dto",dto);
		model.addAttribute("boards",boardList);
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/index";
	}
	@PostMapping("/register")
	public String register(MemberDTO dto,HttpServletRequest request) {
		String email = kakaoLoginService.register(dto);
		if(email!= null) {
			System.out.println(email + "회원 가입완료");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", dto);
		}else {
			System.out.println("회원가입 오류발생");
		}
		return "redirect:/index";
		
	}
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
