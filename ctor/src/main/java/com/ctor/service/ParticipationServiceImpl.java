package com.ctor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctor.dto.ParticipationDTO;
import com.ctor.entity.Participation;
import com.ctor.repository.ParticipationRepository;

@Service
public class ParticipationServiceImpl implements ParticipationService{

	@Autowired
	private ParticipationRepository pRepository;
	
	//참여신청하기(insert)
	@Override
	public Long participate(ParticipationDTO dto) {
		Participation p = dtoToEntity(dto);
		pRepository.save(p);
		return p.getPNo();
	}

	//신청 취소하기(delete)
	@Override
	public Long cancel(Long pno) {
		pRepository.deleteById(pno);
		return pno;
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
		List<Participation> result = pRepository.getByEmail(email);
		List<ParticipationDTO> dtoList = new ArrayList<>();
		
		if (result.size() != 0) {
			for (int i = 0; i < result.size(); i++) {
				dtoList.add(entityToDTO(result.get(i)));
			}
		}
		return dtoList;
	}

}
