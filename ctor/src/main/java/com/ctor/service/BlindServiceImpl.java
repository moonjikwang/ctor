package com.ctor.service;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.dto.BlindPageResultDTO;
import com.ctor.entity.Blind;
import com.ctor.entity.Member;
import com.ctor.repository.BlindCommentsRepository;
import com.ctor.repository.BlindRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 * 
 * @백승연
 * 1. 글 등록 : register()
 * 	  BlindService에서 생성한 dtoToEntity()메소드를 이용
 * 	  dto를 entity로 바꾼 객체를 blindRepository에 저장
 * 	  dto에서 entity로 바꾼 객체(blind)를 Blind_no로 리턴
 * 
 * 2. 게시물 목록 : getList()
 * 	  BlindService에서 생성한 entityToDTO()메소드를 이용하여
 * 	  PageResultDTO 객체를 구성
 * 
 * 3. 글 조회
 * 4. 글 삭제
 * 5. 글 수정
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class BlindServiceImpl implements BlindService{
	
	private final BlindRepository blindRepository;
	private final BlindCommentsRepository commentsRepository;
	
	//글 등록
	@Override
	public Long register(BlindDTO dto) {
		
		log.info(dto);
		
		Blind blind = dtoToEntity(dto);
		blindRepository.save(blind);
		
		return blind.getBno();
	}

	//글 목록
	@Override
	public BlindPageResultDTO<BlindDTO, Object[]> getList(BlindPageRequestDTO blindPageRequestDTO) {
		
		log.info(blindPageRequestDTO);
		
		Function<Object[], BlindDTO> fn
			= (en -> entityToDTO((Blind)en[0], (Member)en[1], (Long)en[2]));
		
		Pageable pageable = blindPageRequestDTO.getPageable(Sort.by("bno").descending());
		Page<Object[]> result = blindRepository.getBlindWithCommentsCount(pageable);
		
		return new BlindPageResultDTO<>(result, fn);
	}

	//글 조회(닉네임)
	@Override
	public BlindDTO findByNickname(String nickName) {
		
		Object result = blindRepository.getBlindByNickname(nickName);
		Object[] arr = (Object[])result;
		
		return entityToDTO((Blind)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	
	//글 삭제
	@Transactional
	@Override
	public void removeWithComments(Long blind_no) {
		
		commentsRepository.deleteBybno(blind_no);
		blindRepository.deleteById(blind_no);
	}

	//글 수정
	@Transactional
	@Override
	public void modify(BlindDTO blindDTO) {
		
		Blind blind = blindRepository.getOne(blindDTO.getBno());
		
		blind.changeTitle(blindDTO.getTitle());
		blind.changeContent(blindDTO.getContent());
		blindRepository.save(blind);
	}
	
}
