package com.ctor.service;

import java.util.List;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;

public interface BoardService {

	Long write(BoardDTO dto);
	Long modify(BoardDTO dto);
	Long delete(Long boardno);
	
	List<BoardDTO> findAllBoards();
	List<BoardDTO> findByEmail(String email);
	List<BoardDTO> findByTech(String tech);
	List<BoardDTO> findByPosition(String position);
	
	default Board boardDTOtoEntity(BoardDTO dto) {
		
		Board board = Board.builder()
				.boardno(dto.getBoardno())
				.title(dto.getTitle())
				.text(dto.getText())
				.category(dto.getCategory())
				.closingDate(dto.getClosingDate())
				.duration(dto.getDuration())
				.groupMember(dto.getGroupMember())
				.viewCount(dto.getViewCount())
				.replyCount(dto.getReplyCount())
				.position(dto.getPosition())
				.techStack(dto.getTechStack())
				.hasTutor(dto.isHasTutor())
				.closed(dto.isClosed())
				.member(Member.builder().email(dto.getMemEmail()).build())
				.build();
				
		return board;
		
	}
	
	default BoardDTO entityToDTO(Board board) {
		
		BoardDTO dto = BoardDTO.builder()
				.boardno(board.getBoardno())
				.title(board.getTitle())
				.text(board.getText())
				.category(board.getCategory())
				.closingDate(board.getClosingDate())
				.duration(board.getDuration())
				.groupMember(board.getGroupMember())
				.viewCount(board.getViewCount())
				.replyCount(board.getReplyCount())
				.position(board.getPosition())
				.techStack(board.getTechStack())
				.hasTutor(board.isHasTutor())
				.closed(board.isClosed())
				.nickName(board.getMember().getNickName())
				.profileImg(board.getMember().getProfileImg())
				.memEmail(board.getMember().getEmail())
				.build();
				
		return dto;
		
	}
	
	
	
	
}
