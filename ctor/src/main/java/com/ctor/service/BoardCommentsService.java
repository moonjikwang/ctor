package com.ctor.service;

import java.util.List;

import com.ctor.dto.BoardCommentsDTO;
import com.ctor.entity.Board;
import com.ctor.entity.BoardComments;
import com.ctor.entity.Member;

public interface BoardCommentsService {

	Long write(BoardCommentsDTO dto);
	Long modify(BoardCommentsDTO dto);
	Long delete(Long boardno);
	
	List<BoardCommentsDTO> findByBno(Long boardno);	//원글번호로 글에 딸린 댓글을 조회
	
	default BoardComments dtoToEntity(BoardCommentsDTO commentsDTO) {
		BoardComments comments = BoardComments.builder()
				.bCommentNo(commentsDTO.getBCommentNo())
				.bCommentText(commentsDTO.getBCommentText())
				.board(Board.builder().boardno(commentsDTO.getBCommentBno()).build())
				.member(Member.builder().email(commentsDTO.getBCommentWriterEmail()).build())
				.build();
		return comments;
	}
	
	default BoardCommentsDTO entityToDTO(BoardComments boardComments) {
		BoardCommentsDTO dto = BoardCommentsDTO.builder()
				.bCommentNo(boardComments.getBCommentNo())
				.bCommentBno(boardComments.getBoard().getBoardno())
				.bCommentRegDate(boardComments.getRegDate())
				.bCommentModDate(boardComments.getModDate())
				.bCommentText(boardComments.getBCommentText())
				.bCommentWriterEmail(boardComments.getMember().getEmail())
				.bCommentNickName(boardComments.getMember().getNickName())
				.bCommentProfileImg(boardComments.getMember().getProfileImg())
				.name(boardComments.getMember().getName())
				.build();
		
		return dto;
		
	}
}
