package com.ctor.board;


import org.springframework.transaction.annotation.Transactional;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.repository.BoardRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.naming.java.javaURLContextFactory;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
//	@Transactional
//	@Commit
//	public void BoardTests() {
//
//		IntStream.rangeClosed(1, 5).forEach(i->{
////			Member member = Member.builder().email("aaa"+i+"@green.com").name("테스터"+i).build();
////			kakaoRepository.save(member);
//			
//			String myString = "20230325";
//			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//			Date mydate = null;
//			try {
//				mydate = dtFormat.parse(myString);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			BoardDTO boardDTO = BoardDTO.builder()
//					
//					.title("스터디 모집 "+i)
//					.text("스터디 상세내용")
//					.category("스터디")
//					.closingDate(mydate)
//					.duration("미정")
//					.groupMember(5)
//					.position("백엔드,프론트엔드,디자이너")
//					.techStack("자바,스프링,HTML")
//					.hasTutor(false)
//					.closed(false)
//					.memEmail("bsyseason0417@gmail.com")
//					.build();
//			boardService.write(boardDTO);
//		});
//		
//		
//	}

//	@Test
//	@Commit
//	public void find() {
//		Optional<Board> result = boardRepository.findById(12l);
//		System.out.println(result.get());
//		
//	
//	}
	
//	@Test
//	public void writeTest() {
//	
//		BoardDTO dto =  BoardDTO.builder()
//				.boardno(4l)
//				.title("수정한 모집")
//				.text("수정한 내용")
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
//		Long bno = boardService.modify(dto);
//		
//		System.out.println(dto);
//		System.out.println(bno);
//				
//	}
//	
//	@Test
//	public void delTest() {
//		boardService.delete(3l);
//	}
//	@Test
//	@Transactional
//	public void findTest() {
//		List<BoardDTO> dtoList =
//		boardService.findByEmail("aaa@naver.com");
//		System.out.println(dtoList);
//	}
//	@Test
//	public void findTest() {
//		List<BoardDTO> dtoList =
//		boardService.findByPosition("디자이너");
//		dtoList.forEach(dto->System.out.println(dto));
//	}
//	@Test
//	@Transactional
//	public void findTest() {
//		List<BoardDTO> dtoList =
//		boardService.findAllBoards();
//		dtoList.forEach(dto->System.out.println(dto));
//	}
//	
	
	@Test
	@Transactional
	public void findTest() {
		BoardDTO dto =
		boardService.findByBno((long)12);
		System.out.println(dto);
	}
	
}
