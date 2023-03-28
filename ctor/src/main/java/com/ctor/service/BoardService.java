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
	BoardDTO findByBno(Long boardno);
	List<BoardDTO> findByEmail(String email);	//작성자 기준으로 조회
	List<BoardDTO> findByTech(String tech);		//기술스택에 tech가 있는 보드 조회
	List<BoardDTO> findByPosition(String position);	//직군에 position가 있는 보드 조회
	List<BoardDTO> findByClosed(Boolean closed);	//마감여부로 조회
	Long viewCount(Long boardno);
	Long autoClose(Long boardno, boolean closing);
	
	List<BoardDTO> findMyProject(String email);	//개설한 프로젝트만 조회
	
	
	
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
				.mentor(dto.getMentorEmail())
				.closed(dto.isClosed())
				.member(Member.builder().email(dto.getMemEmail()).build())
				.build();
				
		return board;
		
	}
	
	default BoardDTO entityToDTO(Board board, Member member, int replyCount, int memberCount) {
		
		BoardDTO dto = BoardDTO.builder()
				.boardno(board.getBoardno())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
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
				.mentorEmail(board.getMentor())
				.closed(board.isClosed())
				.replyCount(replyCount)
				.memberCount(memberCount)
				.memEmail(member.getEmail())
				.nickName(member.getNickName())
				.profileImg(member.getProfileImg())
				.name(member.getName())
				.build();
				
		return dto;
		
	}
	
	
	
	
}