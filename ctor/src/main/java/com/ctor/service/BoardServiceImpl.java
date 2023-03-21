package com.ctor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;
import com.ctor.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	// 글작성
	@Override
	public Long write(BoardDTO dto) {

		Board board = boardDTOtoEntity(dto);
		boardRepository.save(board);
		return board.getBoardno();
	}

	// 글수정
	@Override
	public Long modify(BoardDTO dto) {

		Board board = Board.builder().boardno(dto.getBoardno()).title(dto.getTitle()).text(dto.getText())
				.category(dto.getCategory()).closingDate(dto.getClosingDate()).duration(dto.getDuration())
				.groupMember(dto.getGroupMember()).position(dto.getPosition()).techStack(dto.getTechStack())
				.hasTutor(dto.isHasTutor()).closed(dto.isClosed())
				.member(new Member().builder().email(dto.getMemEmail()).build()).build();

		boardRepository.save(board);
		return board.getBoardno();
	}

	// 글삭제
	@Override
	public Long delete(Long boardno) {
		boardRepository.deleteById(boardno);
		return boardno;
	}

	// 모든 글 조회(수정일 기준 역순)
	@Override
	public List<BoardDTO> findAllBoards() {
		List<Object[]> result = boardRepository.getAll();

		System.out.println(result);
		System.out.println(result.size());

		List<BoardDTO> dtoList = new ArrayList<>();
		if (result.size() != 0) {
			for (int i = 0; i < result.size(); i++) {
										
				dtoList.add(entityToDTO((Board) result.get(i)[0], 
						((Long) result.get(i)[1]).intValue(), 
						((Long) result.get(i)[2]).intValue() ) );
			}
		}
		return dtoList;
	}

	// 작성자ID(email)로 조회
	@Override
	public List<BoardDTO> findByEmail(String email) {
		List<Board> result = boardRepository.getBoardByEmail(email);
		System.out.println(result);

		List<BoardDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
//			dtoList.add(entityToDTO(result.get(i)));
			System.out.println(result.get(i));

		}

		return dtoList;
	}

	// 기술스택으로 조회
	@Override
	public List<BoardDTO> findByTech(String tech) {
		List<Board> result = boardRepository.getBoardByTech(tech);

		List<BoardDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
//			dtoList.add(entityToDTO(result.get(i)));
		}

		return dtoList;
	}

	// 직군으로 조회
	@Override
	public List<BoardDTO> findByPosition(String position) {
		List<Board> result = boardRepository.getBoardByPosition(position);

		List<BoardDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
//			dtoList.add(entityToDTO(result.get(i)));
		}

		return dtoList;
	}

	//선택한 글번호로 조회
	@Override
	public BoardDTO findByBno(Long boardno) {

		Object[] result = boardRepository.getBoardByBoardno(boardno);
		BoardDTO boardDTO = new BoardDTO();
		if (result != null) {
			System.out.println(result);
			System.out.println(result.length);
			System.out.println(Arrays.toString(result));
//			boardDTO = entityToDTO((Board) result[0], 
//					((Long) result[1]).intValue(), 
//					((Long) result[2]).intValue() );
		} else {
			System.out.println("글넘버에 해당하는 데이터를 찾을수 없음");
		}

		return boardDTO;
	}

}
