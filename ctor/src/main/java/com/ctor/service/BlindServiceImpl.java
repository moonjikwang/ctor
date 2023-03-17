package com.ctor.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.dto.BlindPageResultDTO;
import com.ctor.entity.Blind;
import com.ctor.entity.Member;
import com.ctor.repository.BlindRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 * 
 * @백승연
 * 1. 익명게시물 등록 : register()
 * 	  BlindService에서 생성한 dtoToEntity()메소드를 이용
 * 	  dto를 entity로 바꾼 객체를 blindRepository에 저장
 * 	  dto에서 entity로 바꾼 객체(blind)를 Blind_no로 리턴
 *
 * 2. 익명게시물 목록 처리 : getList()
 * 	  BlindService에서 생성한 entityToDTO()메소드를 이용하여
 * 	  PageResultDTO 객체를 구성
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class BlindServiceImpl implements BlindService{
	
	//final 자동주입
	private final BlindRepository blindRepository;

	@Override
	public Long register(BlindDTO dto) {
		
		log.info(dto);
		
		Blind blind = dtoToEntity(dto);
		
		blindRepository.save(blind);
		
		return blind.getBlind_no();
	}

	@Override
	public BlindPageResultDTO<BlindDTO, Object[]> getList(BlindPageRequestDTO blindPageRequestDTO) {
		
		log.info(blindPageRequestDTO);
		
		Function<Object[], BlindDTO> fn
			= (en -> entityToDTO((Blind)en[0], (Member)en[1], (Long)en[2]));
		
		Pageable pageable = blindPageRequestDTO.getPageable(Sort.by("blind_no").descending());
		
		Page<Object[]> result = blindRepository.getBlindWithCommentsCount(pageable);
		
		return new BlindPageResultDTO<>(result, fn);
	}
	
}
