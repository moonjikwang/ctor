package com.ctor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctor.dto.BoardDTO;
import com.ctor.dto.ParticipationDTO;
import com.ctor.service.BoardService;
import com.ctor.service.ParticipationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ParticipationController {

	private final ParticipationService pService;
	private final BoardService bService;
	
	//신청하기
	@PostMapping("participate")
	public void participate(ParticipationDTO dto) {
		boolean closing = pService.participate(dto);
		bService.autoClose(dto.getPBno(), closing);
		
	}
	
	//신청 취소하기
	@PostMapping("cancel")
	public void cancel(Long pno) {
		Long bno = pService.findByPno(pno).getPBno();
		boolean closing = pService.cancel(pno);
		bService.autoClose(bno, closing);
	}
}
