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

import javax.persistence.Tuple;

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
////		IntStream.rangeClosed(1, 5).forEach(i->{
//////			Member member = Member.builder().email("aaa"+i+"@green.com").name("테스터"+i).build();
//////			kakaoRepository.save(member);
////			
//			String myString = "20230404";
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
//					.title("스터디 모집 ")
//					.text("프로젝트 상세내용")
//					.category("프로젝트")
//					.closingDate(mydate.toString())
//					.duration("3w")
//					.groupMember((int)(Math.random()*5+2))
//					.position("백엔드,디자이너")
//					.techStack("JAVA,JSP")
//					.hasTutor(false)
//					.closed(false)
//					.memEmail("aaa@green.com")
//					.build();
//			boardService.write(boardDTO);
////		});
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
//	@Transactional
//	public void find() {
//		List<Object[]> result = boardRepository.getBoardByEmail("moonjikwang@naver.com");
//		result.forEach(i->{
//			System.out.println("조회결과");
//			System.out.println(i[0]);
//			System.out.println(i[1]);
//		});
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
	@Test
	@Transactional
	public void findTest() {
	//레포지토리 테스트
//		List<Object[]> result = boardRepository.getBoardByTech("JAVA");
//		result.forEach(arr->{
//			System.out.println((Board)arr[0]);
//			System.out.println((Member)arr[1]);
//			System.out.println((Long)arr[2]);
//			System.out.println((Long)arr[3]);
//		});
//		List<Object[]> result = boardRepository.getBoardByPosition("프론트엔드");
//		result.forEach(arr->{
//			System.out.println((Board)arr[0]);
//			System.out.println((Member)arr[1]);
//			System.out.println((Long)arr[2]);
//			System.out.println((Long)arr[3]);
//		});
		
	//서비스 테스트
		List<BoardDTO> dtoList =
		boardService.findByTech("MariaDB");
		System.out.println(dtoList);
		dtoList.forEach(b->System.out.println(b));
	}
//	@Test
//	public void findTest() {
//		List<BoardDTO> dtoList =
//		boardService.findByPosition("디자이너");
//		dtoList.forEach(dto->System.out.println(dto));
//	}
//	@Test
//	@Transactional
//	public void findTest() {
//		BoardDTO dto =
//		boardService.findByBno(5L);
//		System.out.println(dto);
//		
////		Tuple result = boardRepository.getBoardByBoardno(5L);
////		Board board = (Board) result.get(0);
////		Member member = (Member) result.get(1);
////		int c = ((Long)result.get(2)).intValue();
////		int p = ((Long)result.get(3)).intValue();
////		System.out.println(board);
////		System.out.println(member);
////		System.out.println(c);
////		System.out.println(p);
//	}
//	@Test
//	public void findTest() {
//		List<BoardDTO> dtoList =
//		boardService.findAllBoards();
//		dtoList.forEach(dto->System.out.println(dto));
//	}
//	
	
}
