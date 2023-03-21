package com.ctor.board;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ctor.dto.BoardDTO;
import com.ctor.entity.Board;
import com.ctor.entity.BoardComments;
import com.ctor.entity.Member;
import com.ctor.repository.BoardCommentsRepository;
import com.ctor.repository.BoardRepository;
import com.ctor.service.BoardService;

@SpringBootTest
public class BoardCommentTest {

	@Autowired
	BoardCommentsRepository commentsRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardService boardService;
	
//	@Test
//	public void addComment() {
//		IntStream.rangeClosed(1, 20).forEach(i->{
//		BoardComments comments = BoardComments.builder()
//				.bCommentText("댓글"+i)
//				.member(new Member().builder().email("moonjikwang@naver.com").build())
//				.board(new Board().builder().boardno( (long) (int)(Math.random()*5+1) ).build())
//				.build();
//		commentsRepository.save(comments);
//		});
//	}
//	@Test
//	public void selectTest() {
//		List<Object[]> boards = boardRepository.getAll();
//		
//		boards.forEach(b->{
//			System.out.println("조회된 내용 ");
//			for (int i = 0; i < b.length; i++) {
//				
//				System.out.println(b[i]);
//			}
//		});
//	}
//	@Test
//	@Transactional
//	public void selectTest() {
//		List<BoardDTO> boardDTOs = boardService.findAllBoards();
//		
//		boardDTOs.forEach(b->{
//			System.out.println(b);
//		});
//	}
//	
//	@Test
//	public void memCnt() {
//		IntStream.rangeClosed(1, 5).forEach(i->{
//			System.out.println(i+"번 글 멤버수 : ");
//			System.out.println(boardRepository.getMemberCountByBoardno((long)i));
//		});
//		
//	}
}


