package com.ctor.service;

import java.util.List;

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
 * 4. 코멘트 삭제 : removeWithComments()
 */
public interface BlindService {
	
	Long register(BlindDTO dto);
	
	BlindPageResultDTO<BlindDTO, Object[]> getList(BlindPageRequestDTO blindPageRequestDTO);
	
	BlindDTO findByNickname(String nickName);
	
	void removeWithComments(Long blind_no);
	void modify(BlindDTO blindDTO);
	
	default Blind dtoToEntity(BlindDTO dto) {
		
		//실제 DB에 있는 email 사용 (nickName?)
		Member member = Member.builder().email(dto.getWriter()).build();
		
		Blind blind = Blind.builder()
				.blind_no(dto.getBlind_no())
				.blind_title(dto.getTitle())
				.blind_content(dto.getContent())
				.writer(member)
				.build();
		
		return blind;
	}
	
	default BlindDTO entityToDTO(Blind blind, Member member, Long replyCount) {
		
		BlindDTO blindDTO = BlindDTO.builder()
				.blind_no(blind.getBlind_no())
				.title(blind.getBlind_title())
				.content(blind.getBlind_content())
				.regDate(blind.getRegDate())
				.modDate(blind.getModDate())
				.writer(member.getNickName())
				.writer(member.getEmail())
				.reply_count(replyCount.intValue())	//long으로 나오므로 int처리
				.build();
		
		return blindDTO;
	}
}
