package com.ctor.service;

import java.util.List;

import com.ctor.dto.ParticipationDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;
import com.ctor.entity.Participation;

public interface ParticipationService {

	boolean participate(ParticipationDTO dto);	//참여신청하기(insert): 신청 후 프로젝트 참여자 꽉 찼는지 여부를 리턴
	boolean cancel(Long pno);	//신청 취소하기(delete)
	
	ParticipationDTO findByPno(Long pno);	//해당 신청 조회하기
	List<ParticipationDTO> findByBno(Long bno);	//플젝,스터디로 조회하기
	List<ParticipationDTO> findByEmail(String email);	//참여자로 조회하기
	
	default ParticipationDTO entityToDTO(Participation p) {
		ParticipationDTO dto = ParticipationDTO.builder()
				.pNo(p.getPNo())
				.title(p.getBoard().getTitle())
				.pRegDate(p.getRegDate())
				.pModDate(p.getModDate())
				.pBno(p.getBoard().getBoardno())
				.pMemEmail(p.getMember().getEmail())
				.pNickName(p.getMember().getNickName())
				.pProfileImg(p.getMember().getProfileImg())
				.pName(p.getMember().getName())
				.build();
		return dto;
		
	}
	
	default Participation dtoToEntity(ParticipationDTO dto) {
		Participation p = Participation.builder()
				.pNo(dto.getPNo())
				.board(Board.builder().boardno(dto.getPBno()).build())
				.member(Member.builder().email(dto.getPMemEmail()).build())
				.build();
		return p;
	}
	
	
}
