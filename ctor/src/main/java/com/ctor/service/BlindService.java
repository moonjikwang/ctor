package com.ctor.service;

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.dto.BlindPageResultDTO;
import com.ctor.entity.Blind;
import com.ctor.entity.Member;
/**
 * 
 * @백승연
 * 1. 익명게시물 등록
 * 	  dtoToEntity() : BlindServiceImpl의 register()에서 사용(실제로 게시물을 등록한다)
 * 	  BlindDTO를 -> Blind Entity로 타입변환(dtoToEntity)
 *	  생성된 게시물의 객체를 반환한다.
 *
 * 2. 익명게시물 목록 처리
 * 	  entityToDTO() : BlindServiceImpl의 getList()에서 사용
 * 	  Object[]를 -> DTO로 타입변환(entityToDTO)
 * 	  Object[] : Blind, Member, 댓글수(Long타입) 
 */
public interface BlindService {
	
	Long register(BlindDTO dto);
	
	BlindPageResultDTO<BlindDTO, Object[]> getList(BlindPageRequestDTO blindPageRequestDTO);
	
	
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
