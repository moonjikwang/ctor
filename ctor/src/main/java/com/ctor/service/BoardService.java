package com.ctor.service;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;

public interface BoardService {

	Long write(BoardDTO dto);
	Long delete(Long boardno);
	
	default Board boardDTOtoEntity(BoardDTO dto) {
		
		Board board = Board.builder()
				.boardno(dto.getBoardno())
				.title(dto.getTitle())
				.text(dto.getText())
				.closingDate(dto.getClosingDate())
				.projStartDate(dto.getProjStartDate())
				.projEndDate(dto.getProjEndDate())
				.duration(dto.getDuration())
				.groupMember(dto.getGroupMember())
				.replyCount(dto.getReplyCount())
				.position(dto.getPosition())
				.techStack(dto.getTechStack())
				.hasTutor(dto.isHasTutor())
				.member(Member.builder().email(dto.getMemEmail()).build())
				.build();
				
		return board;
		
	}
	
	
	
}
