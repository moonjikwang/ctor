package com.ctor.board;


import org.springframework.transaction.annotation.Transactional;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.repository.BoardRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.entity.Member;
import com.ctor.repository.KakaoRepository;
import com.ctor.service.BoardService;

@SpringBootTest
public class BoardTests {

	@Autowired
	private KakaoRepository kakaoRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private BoardService boardService;
	
//	@Test
//	public void BoardTests() {
//
//		IntStream.rangeClosed(1, 5).forEach(i->{
//			Board board = Board.builder()
//					.title("그냥 수동으로 넣음"+i)
//					.text("날짜는 알수가 없음")
//					.projStartDate(new Date())
//					.duration("미정")
//					.closingDate(new Date())
//					.groupMember(5)
//					.hasTutor(false)
//					.member(Member.builder().email("aaa1@naver.com").build())
//					.build();
//			boardRepository.save(board);
//		});
//		
//		
//	}

//	@Test
//	@Transactional
//	public void find() {
//		Optional<Board> result = boardRepository.findById(25l);
//		System.out.println(result.get());
//		
//	
//	}
	
//	@Test
//	public void writeTest() {
//	
//		BoardDTO dto =  BoardDTO.builder()
//				.title("3번째 모집")
//				.text("새로 작성한 내용")
//				.closingDate(new Date())
//				.projStartDate(new Date())
//				.projEndDate(new Date())
//				.duration("미정")
//				.groupMember(10)
//				.position("백엔드,프론트엔드,디자이너")
//				.techStack("자바,스프링,타임리프")
//				.hasTutor(false)
//				.memEmail("aaa1@naver.com")
//				.build();
//		
//		Long bno = boardService.write(dto);
//		
//		System.out.println(dto);
//		System.out.println(bno);
//				
//	}
	
//	@Test
//	public void delTest() {
//		boardService.delete(3l);
//	}
	@Test
	public void findTest() {
		List<BoardDTO> dtoList =
		boardService.findByEmail("aaa1@green.com");
		System.out.println(dtoList);
	}
//	@Test
//	public void findTest() {
//		BoardDTO dto =
//		boardService.findByBoardno(2l);
//		System.out.println(dto);
//	}
	
	
}
