package com.ctor.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;

public interface BoardService {

	Long write(BoardDTO dto);
	Long modify(BoardDTO dto);
	Long delete(Long boardno);
	
	List<BoardDTO> findAllBoards();
	BoardDTO findByBno(Long boardno);
	List<BoardDTO> findByEmail(String email);
	List<BoardDTO> findByTech(String tech);
	List<BoardDTO> findByPosition(String position);
	
	default Board boardDTOtoEntity(BoardDTO dto) {
		
		Board board = Board.builder()
				.boardno(dto.getBoardno())
				.title(dto.getTitle())
				.text(dto.getText())
				.category(dto.getCategory())
				.chatLink(dto.getChatLink())
				.closingDate(dto.getClosingDate())
				.duration(dto.getDuration())
				.groupMember(dto.getGroupMember())
				.viewCount(dto.getViewCount())
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
				.chatLink(board.getChatLink())
				.position(board.getPosition())
				.techStack(board.getTechStack())
				.hasTutor(board.isHasTutor())
				.closed(board.isClosed())
				.nickName(board.getMember().getNickName())
				.profileImg(board.getMember().getProfileImg())
				.replyCount(0)
				.memberCount(0)
				.memEmail(board.getMember().getEmail())
				.nickName(board.getMember().getNickName())
				.profileImg(board.getMember().getProfileImg())
				.build();
				
		return dto;
		
	}
	
	
	
	
}
