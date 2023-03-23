package com.ctor.service;

import java.util.List;

import com.ctor.dto.BlindCommentsDTO;
import com.ctor.dto.BlindDTO;
import com.ctor.entity.Blind;
import com.ctor.entity.BlindComments;
import com.ctor.entity.Member;

public interface BlindCommentsService {

	Long register(BlindCommentsDTO blindCommentsDTO);//댓글 등록
	List<BlindCommentsDTO> getList(Long bno); 		 //특정 게시물의 댓글 목록
	void modify(BlindCommentsDTO blindCommentsDTO);  //댓글 수정
<<<<<<< HEAD
	void remove(Long cno); 							 //댓글삭제
	BlindCommentsDTO getDTO(Long cno);
=======
	void remove(Long cno); 							 //댓글 삭제
>>>>>>> branch 'main' of https://github.com/moonjikwang/ctor.git
	
	//BlindCommentsDTO -> BlindComments객체로 변환
	default BlindComments dtoToEntity(BlindCommentsDTO blindCommentsDTO) {
		Blind blind = Blind.builder().bno(blindCommentsDTO.getBno()).build();
		
		BlindComments blindComments = BlindComments.builder()
				.cno(blindCommentsDTO.getCno())
				.writer(Member.builder().email(blindCommentsDTO.getEmail()).build())
				.blindCtext(blindCommentsDTO.getText())
				.blindCreplyer(blindCommentsDTO.getReplyer())
				.blind(blind)
				.build();
		
		return blindComments;
	}
	
	//BlindComments객체 -> BlindCommentsDTO로 변환, Blind객체 필요 없으므로 게시물 번호만 가져온다
	default BlindCommentsDTO entityToDTO(BlindComments blindComments) {
		BlindCommentsDTO dto = BlindCommentsDTO.builder()
				.profileImg(blindComments.getWriter().getProfileImg())
				.cno(blindComments.getCno())
				.text(blindComments.getBlindCtext())
				.replyer(blindComments.getBlindCreplyer())
				.regDate(blindComments.getRegDate())
				.modDate(blindComments.getModDate())
				.build();
		
		return dto;
	}
	
}
