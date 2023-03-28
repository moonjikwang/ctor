package com.ctor.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctor.dto.ParticipationDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;
import com.ctor.entity.Participation;
import com.ctor.repository.BoardRepository;
import com.ctor.repository.KakaoRepository;
import com.ctor.repository.ParticipationRepository;

@Service
public class ParticipationServiceImpl implements ParticipationService{

	@Autowired
	private ParticipationRepository pRepository;
	@Autowired
	private KakaoRepository kakaoRepository;
	
	//참여신청하기(insert) 
	@Override
	public boolean participate(ParticipationDTO dto) {
		Participation p = dtoToEntity(dto);
		pRepository.save(p);
		
		int groupMem = p.getBoard().getGroupMember();
		int memberCnt = pRepository.getMemberCount(dto.getPBno());
		boolean closing = false;	//기본적으로 마감 전
	
		if (memberCnt >= groupMem) {
			closing = true;			//인원이 넘으면 마감
		}
			
		return closing;
	}

	//신청 취소하기(delete)
	@Override
	public boolean cancel(Long pno) {
		
		Participation p = pRepository.findById(pno).get();
		int groupMem = p.getBoard().getGroupMember();
		int memberCnt = pRepository.getMemberCount(p.getBoard().getBoardno()) -1;
		
		boolean closing = false;	//기본적으로 마감 전
	
		if (memberCnt >= groupMem) {
			closing = true;			//인원이 넘으면 마감
		}
	
		pRepository.delete(p);
		
		return closing;
	}
	
	//해당 신청 조회하기
	@Override
	public ParticipationDTO findByPno(Long pno) {
		ParticipationDTO dto = entityToDTO(pRepository.findById(pno).get());
		return dto;
	}

	//플젝,스터디로 조회하기
	@Override
	public List<ParticipationDTO> findByBno(Long bno) {
		List<Participation> result = pRepository.getByBno(bno);
		List<ParticipationDTO> dtoList = new ArrayList<>();
		
		if (result.size() != 0) {
			for (int i = 0; i < result.size(); i++) {
				dtoList.add(entityToDTO(result.get(i)));
			}
		}
		return dtoList;
	}

	//참여자로 조회하기
	@Override
	public List<ParticipationDTO> findByEmail(String email) {
		List<Participation> result = pRepository.findByMember(kakaoRepository.findById(email).get());
		System.out.println("1111111"+result);
		List<ParticipationDTO> dtoList = new ArrayList<>();
		
		if (result.size() != 0) {
			for (int i = 0; i < result.size(); i++) {
				dtoList.add(entityToDTO(result.get(i)));
			}
		}
		System.out.println("22222"+dtoList);
		return dtoList;
	}

	

	

}
