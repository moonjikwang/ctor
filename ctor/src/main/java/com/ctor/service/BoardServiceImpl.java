package com.ctor.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public Long write(BoardDTO dto) {
		
		Board board = boardDTOtoEntity(dto);
		boardRepository.save(board);
		return board.getBoardno();
	}

	@Override
	public Long delete(Long boardno) {
		boardRepository.deleteById(boardno);
		return boardno;
	}

}
