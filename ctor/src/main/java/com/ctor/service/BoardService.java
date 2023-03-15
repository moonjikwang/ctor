package com.ctor.service;

import java.util.List;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;

public interface BoardService {

	Long write(BoardDTO dto);
	Long delete(Long boardno);
	List<BoardDTO> findByEmail(String email);
	BoardDTO findByBoardno(Long boardno);
	
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
	
	default BoardDTO entityToDTO(Board board) {
		
		BoardDTO dto = BoardDTO.builder()
				.boardno(board.getBoardno())
				.title(board.getTitle())
				.text(board.getText())
				.closingDate(board.getClosingDate())
				.projStartDate(board.getProjStartDate())
				.projEndDate(board.getProjEndDate())
				.duration(board.getDuration())
				.groupMember(board.getGroupMember())
				.replyCount(board.getReplyCount())
				.position(board.getPosition())
				.techStack(board.getTechStack())
				.hasTutor(board.isHasTutor())
				.memEmail(board.getMember().getEmail())
				.build();
				
		return dto;
		
	};
	
	
	
}
