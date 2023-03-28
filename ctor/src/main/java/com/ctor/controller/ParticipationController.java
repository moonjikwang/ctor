package com.ctor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String participate(ParticipationDTO dto) {
		boolean closing = pService.participate(dto);
		bService.autoClose(dto.getPBno(), closing);
		return "<script type='text/javascript'>"
                + "alert('신청이 완료되었습니다.');"
                + "location.href = document.referrer;"
                +"</script>";
	}
	
	//신청 취소하기
	@PostMapping("cancel")
	public String cancel(Long bno, String email) {
		List<ParticipationDTO> list = pService.findByEmail(email);
		ParticipationDTO dto = findParticipationByEmail(list, bno);
		boolean closing = pService.cancel(dto.getPNo());
		bService.autoClose(bno, closing);
		return "<script type='text/javascript'>"
        + "alert('신청취소가 완료되었습니다.');"
        + "location.href = document.referrer;"
        +"</script>";
}
	public ParticipationDTO findParticipationByEmail(List<ParticipationDTO> participationDTOs, Long bno) {
	    for (ParticipationDTO participationDTO : participationDTOs) {
	        if (participationDTO.getPBno().equals(bno)) {
	            return participationDTO;
	        }
	    }
	    return null;
	}
}
