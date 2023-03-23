package com.ctor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Tuple;

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

		Board board = Board.builder()
				.boardno(dto.getBoardno())
				.title(dto.getTitle())
				.text(dto.getText())
				.category(dto.getCategory())
				.closingDate(dto.getClosingDate())
				.duration(dto.getDuration())
				.groupMember(dto.getGroupMember())
				.position(dto.getPosition())
				.techStack(dto.getTechStack())
				.hasTutor(dto.isHasTutor())
				.closed(dto.isClosed())
				.member(new Member().builder().email(dto.getMemEmail()).build())
				.build();

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
						(Member) result.get(i)[1],
						((Long) result.get(i)[2]).intValue(), 
						((Long) result.get(i)[3]).intValue() ) );
			}
		}
		return dtoList;
	}

	// 작성자ID(email)로 조회
	@Override
	public List<BoardDTO> findByEmail(String email) {
		List<Object[]> result = boardRepository.getBoardByEmail(email);
		System.out.println(result);

		List<BoardDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
			dtoList.add(entityToDTO((Board) result.get(i)[0], 
					(Member) result.get(i)[1],
					((Long) result.get(i)[2]).intValue(), 
					((Long) result.get(i)[3]).intValue() ) );
			System.out.println(result.get(i));

		}

		return dtoList;
	}

	// 기술스택으로 조회
	@Override
	public List<BoardDTO> findByTech(String tech) {
		List<Object[]> result = boardRepository.getBoardByTech(tech);

		List<BoardDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
			dtoList.add(entityToDTO((Board) result.get(i)[0], 
					(Member) result.get(i)[1],
					((Long) result.get(i)[2]).intValue(), 
					((Long) result.get(i)[3]).intValue() ) );
		}

		return dtoList;
	}

	// 직군으로 조회
	@Override
	public List<BoardDTO> findByPosition(String position) {
		List<Object[]> result = boardRepository.getBoardByPosition(position);

		List<BoardDTO> dtoList = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
			dtoList.add(entityToDTO((Board) result.get(i)[0], 
					(Member) result.get(i)[1],
					((Long) result.get(i)[2]).intValue(), 
					((Long) result.get(i)[3]).intValue() ) );
		}

		return dtoList;
	}

	//선택한 글번호로 조회
	@Override
	public BoardDTO findByBno(Long boardno) {

		Tuple result = boardRepository.getBoardByBoardno(boardno);
		
		Board board = (Board) result.get(0);
		Member member = (Member) result.get(1);
		int replyCount = ((Long)result.get(2)).intValue();
		int memberCount = ((Long)result.get(3)).intValue();
		
		BoardDTO boardDTO = entityToDTO(board, member, replyCount, memberCount);
		
		return boardDTO;
	}

	//조회수 세팅
	@Override
	public Long viewCount(Long boardno) {
		Board board = boardRepository.findById(boardno).get();
		board.setViewCount(board.getViewCount()+1);
		boardRepository.save(board);
		return null;
	}
	
	//자동마감처리
	@Override
	public Long autoClose(Long boardno, boolean closing) {
		Board board = boardRepository.findById(boardno).get();
		board.setClosed(closing);
		boardRepository.save(board);
		return boardno;
	}

	@Override
	public List<BoardDTO> findMyProject(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
