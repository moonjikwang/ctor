package com.ctor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.dto.BlindPageResultDTO;
import com.ctor.entity.Blind;
import com.ctor.entity.Member;

/**
 * 
 * @백승연
 * 1. 글 등록 : register()
 * 	  dtoToEntity() : BlindServiceImpl의 register()에서 사용(실제로 게시물을 등록한다)
 * 	  BlindDTO를 -> Blind Entity로 타입변환(dtoToEntity)
 *	  생성된 게시물의 객체를 반환한다.
 *
 * 2. 글 목록 : getList()
 * 	  entityToDTO() : BlindServiceImpl의 getList()에서 사용
 * 	  Object[]를 -> DTO로 타입변환(entityToDTO)
 * 	  Object[] : Blind, Member, 댓글수(Long타입)
 * 
 * 3. 글 조회 : findByNickname()
 * 	  BlindServiceImpl의 findByNickname()를 이용해 처리
 * 
 * 4. 글 삭제 : removeWithComments()
 * 5. 글 수정 : modify()
 * 6. 검색기능과 검색한글 목록화 : searchBlinds(), searchBlindsList()
 * 7. 사용자가 작성한 게시글 조회
 */
public interface BlindService {
	
	BlindDTO findById(Long bno);
	Long register(BlindDTO dto);
	BlindPageResultDTO<BlindDTO, Object[]> getList(BlindPageRequestDTO blindPageRequestDTO);
	BlindDTO findByNickname(String nickName);
	void removeWithComments(Long blind_no);
	void modify(BlindDTO blindDTO);
	List<Blind> searchBlinds(String keyword);
	Page<Blind> searchBlindsList(String keyword, Pageable pageable);
	List<Object[]> findMyBlindPost(String nickName); 
	List<BlindDTO> findAll();
	
	default Blind dtoToEntity(BlindDTO dto) {
		//실제 DB에 있는 email 사용
		Member memberEmail = Member.builder().email(dto.getWriter()).build();
		
		Blind blind = Blind.builder()
				.bno(dto.getBno())
				.blindTitle(dto.getTitle())
				.blindContent(dto.getContent())
				.writer(memberEmail)
				.build();
		
		return blind;
	}
	
	default BlindDTO entityToDTO(Blind blind, Member member, Long replyCount) {
		BlindDTO blindDTO = BlindDTO.builder()
				.bno(blind.getBno())
				.title(blind.getBlindTitle())
				.content(blind.getBlindContent())
				.regDate(blind.getRegDate())
				.modDate(blind.getModDate())
				.nickName(member.getNickName())
				.writer(member.getEmail())
				.replyCount(replyCount.intValue())	//long으로 나오므로 int처리
				.build();
		
		return blindDTO;
	}
	
}
