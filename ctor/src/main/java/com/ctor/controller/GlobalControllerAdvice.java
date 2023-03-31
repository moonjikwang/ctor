package com.ctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ctor.dto.AlarmDTO;
import com.ctor.dto.MemberDTO;
import com.ctor.service.AlarmService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	AlarmService alarmService;
	
    @ModelAttribute("message")
    public List<AlarmDTO> message(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
        	if(session.getAttribute("userInfo") != null) {
        	MemberDTO member = (MemberDTO) session.getAttribute("userInfo");
        	return alarmService.findbyEmail(member.getEmail());
        	}else {
				return null;
			}
        }else {
        	return null;
        }
    }
    
}
