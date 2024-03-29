package com.ctor.service;

import java.util.ArrayList;
import java.util.List;
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
import com.ctor.entity.Board;
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
 * 6. 로그인한 사용자가 작성한 게시글 조회
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

	@Override
	public BlindDTO findById(Long bno) {
		Blind entity = blindRepository.findById(bno).get();
		BlindDTO dto = entityToDTO(entity, Member.builder()
				.email(entity.getWriter().getEmail())
				.nickName(entity.getWriter().getNickName())
				.build()
				,Long.valueOf(commentsRepository.getCommentsByBlind(entity).size()));
		return dto;
	}

	//검색 기능(제목)
	@Transactional
	@Override
	public List<Blind> searchBlinds(String keyword) {
		List<Blind> searchList = blindRepository.findByBlindTitleContaining(keyword);
		return searchList;
	}

	//검색 후 목록 처리
	@Transactional
	@Override
	public Page<Blind> searchBlindsList(String keyword, Pageable pageable) {
		Page<Blind> searchList = blindRepository.findByBlindTitleContaining(keyword, pageable);
		return searchList;
	}

	//로그인한 사용자가 작성한 게시글 조회
	@Override
	public List<Object[]> findMyBlindPost(String nickName) {
		List<Object[]> postList = blindRepository.findBlindByMemberNickname(nickName);
		return postList;
	}

	@Override
	public List<BlindDTO> findAll() {
		List<BlindDTO> blindList = new ArrayList<>();
		blindRepository.findAll().forEach(entity -> blindList.add(entityToDTO(entity, Member.builder().email(entity.getWriter().getEmail()).build(), Long.valueOf(commentsRepository.getCommentsByBlind(entity).size()))));
		return blindList;
	}

	//조회수 세팅
	@Transactional
	@Override
	public Long viewCount(Long bno) {
		Blind blind = blindRepository.findById(bno).get();
		blind.setViewCount(blind.getViewCount()+1);
		blindRepository.save(blind);
		return null;
	}
	
	//조회수 세팅2
//	@Transactional
//	@Override
//	public Long viewCount(Long bno) {
//		return blindRepository.updateViewCount(bno);
//	}
	
}
