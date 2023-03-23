package com.ctor.board;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.ctor.dto.ParticipationDTO;
import com.ctor.entity.Board;
import com.ctor.entity.Member;
import com.ctor.entity.Participation;
import com.ctor.repository.ParticipationRepository;
import com.ctor.service.BoardService;
import com.ctor.service.ParticipationService;

@SpringBootTest
public class ParticipationTests {

	@Autowired
	ParticipationRepository pRepository;
	
	@Autowired
	ParticipationService pService;
	
	@Autowired
	BoardService bService;
	
//	@Test
//	public void participationTest() {
//		
//		IntStream.rangeClosed(1, 20).forEach(i->{
//		Participation participation = Participation.builder()
//				.board(new Board().builder().boardno( (long) (int)(Math.random()*5+1)).build() )
//				.member(new Member().builder().email("moonjikwang@naver.com").build())
//				.build();
//		participationRepository.save(participation);
//		});
//	}
	
//	@Test
//	public void partSelectTest() {
//		List<Participation> result = pRepository.getByBno(1L);
//		result.forEach(p->{
//			System.out.println(p.getBoard().getBoardno());
//			System.out.println(p);
//		});
//	}
//	@Test
//	public void partInsertTest() {
//		ParticipationDTO dto = ParticipationDTO.builder()
//				.pBno(3L)
//				.pMemEmail("aaa@green.com")
//				.build();
//		System.out.println(pService.participate(dto));
//		
//	}
//	@Test
//	public void partInsertControllerTest() {
//		ParticipationDTO dto = ParticipationDTO.builder()
//				.pBno(3L)
//				.pMemEmail("aaa@green.com")
//				.build();
//		System.out.println(pService.participate(dto));
//		boolean closing = pService.participate(dto);
//		bService.autoClose(dto.getPBno(), closing);
//		
//	}
	@Test
	@Transactional
	@Commit
	public void partDelTest() {
		Long pno = 23L;
		Long bno = pService.findByPno(pno).getPBno();
		
		boolean closing = pService.cancel(pno);
		bService.autoClose(bno, closing);
		System.out.println(bno+" "+closing);
	}
//	@Test
//	public void findByBnoTest() {
//		List<ParticipationDTO> result = pService.findByBno(1L);
//		result.forEach(dto->System.out.println(dto));
//		
//	}
//	@Test
//	public void findByEmailTest() {
//		List<ParticipationDTO> result = pService.findByEmail("aaa@green.com");
//		result.forEach(dto->System.out.println(dto));
//		
//	}
//	@Test
//	public void getMemberCountTest() {
//		System.out.println(pRepository.getMemberCount(5L));
//		
//	}
}
