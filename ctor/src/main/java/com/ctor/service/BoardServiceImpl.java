package com.ctor.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	@Override
	public List<BoardDTO> findByEmail(String email) {
		Object[] result = boardRepository.getBoardByEmail(email);
		List<BoardDTO> dtoList = null;
		
		for (int i = 0; i < result.length; i++) {
			Board board =  (Board) result[i];
			dtoList.add(entityToDTO(board));
		}
	
		return dtoList;
	}

	@Override
	public BoardDTO findByBoardno(Long boardno) {

//		Object result = boardRepository.getBoardWithWriter(boardno);
//		BoardDTO dto = (BoardDTO) result;
//		
//		return dto;
		return null;
	}
	

	
	

}
