package com.ctor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctor.dto.BoardCommentsDTO;
import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.BoardComments;
import com.ctor.entity.Member;
import com.ctor.repository.BoardCommentsRepository;

@Service
public class BoardCommentsServiceImpl implements BoardCommentsService {

	@Autowired
	private BoardCommentsRepository commentsRepository;

	@Override
	public Long write(BoardCommentsDTO dto) {
		BoardComments comments = dtoToEntity(dto);
		commentsRepository.save(comments);
		return comments.getBCommentNo();
	}

	@Override
	public Long modify(BoardCommentsDTO dto) {
		BoardComments comments = BoardComments.builder()
				.bCommentNo(dto.getBCommentNo())
				.bCommentText(dto.getBCommentText())
				.member(Member.builder().email(dto.getBCommentWriterEmail()).build())
				.board(Board.builder().boardno(dto.getBCommentBno()).build())
				.build();
		commentsRepository.save(comments);
		return comments.getBCommentNo();
	}

	@Override
	public Long delete(Long boardno) {
		commentsRepository.deleteById(boardno);
		return boardno;
	}

	//원글번호로 글에 딸린 댓글을 조회
	@Override
	public List<BoardCommentsDTO> findByBno(Long boardno) {
		List<BoardComments> result = commentsRepository.getByBno(boardno);
		
		List<BoardCommentsDTO> dtoList = new ArrayList<>();
		
		if (result.size() != 0) {
			for (int i = 0; i < result.size(); i++) {
				dtoList.add(entityToDTO(result.get(i)));
			}
		}
		
		return dtoList;
	}

	@Override
	public List<BoardCommentsDTO> findByEmail(String email) {
		List<BoardComments> result = commentsRepository.findByMember(Member.builder().email(email).build());

		List<BoardCommentsDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
			dtoList.add(entityToDTO(result.get(i)));

		}
		return dtoList;
	}
	
}
