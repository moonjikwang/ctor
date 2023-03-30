package com.ctor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctor.dto.BoardDTO;
import com.ctor.dto.MemberDTO;
import com.ctor.dto.ParticipationDTO;
import com.ctor.service.BoardService;
import com.ctor.service.KakaoLoginService;
import com.ctor.service.ParticipationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ParticipationController {

	private final ParticipationService pService;
	private final BoardService bService;
	private final KakaoLoginService kakaoLoginService;
	
	//신청하기
	@PostMapping("participate")
	public String participate(ParticipationDTO dto) {
		MemberDTO member = kakaoLoginService.findByEmail(dto.getPMemEmail());
		if(member.getGrade().equals("teacher")) {
			BoardDTO board = bService.findByBno(dto.getPBno());
			if(board.getMentorEmail() != null) {
				return "<script type='text/javascript'>"
		                + "alert('이미 멘토가 있습니다.');"
		                + "location.href = document.referrer;"
		                +"</script>";
			}
			bService.Mentor(dto.getPBno(), dto.getPMemEmail());
		}
		
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
		MemberDTO member = kakaoLoginService.findByEmail(email);
		if(member.getGrade().equals("teacher")) {
			bService.deleteMentor(bno);
		}
		
		
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
