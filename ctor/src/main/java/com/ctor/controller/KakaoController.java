package com.ctor.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctor.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class KakaoController {

	private final KakaoLoginService kakaoLoginService;
	// 1번 카카오톡에 사용자 코드 받기(jsp의 a태그 href에 경로 있음)
	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code,HttpServletRequest request) throws Throwable {

		// 코드확인
		System.out.println("code:" + code);
		// 사용자코드로 엑세스 토큰 발급받기
		String access_Token = kakaoLoginService.getAccessToken(code);
		//엑세스 토큰으로 사용자 정보확인
		HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###email#### : " + userInfo.get("email"));
		System.out.println("###profileImg#### : " + userInfo.get("profileImg"));
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", userInfo);
		return "redirect:/index";
		// return에 페이지를 해도 되고, 여기서는 코드가 넘어오는지만 확인할거기 때문에 따로 return 값을 두지는 않았음

	}
}