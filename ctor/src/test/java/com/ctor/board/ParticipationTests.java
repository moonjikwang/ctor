package com.ctor.board;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.entity.Board;
import com.ctor.entity.Member;
import com.ctor.entity.Participation;
import com.ctor.repository.ParticipationRepository;

@SpringBootTest
public class ParticipationTests {

	@Autowired
	ParticipationRepository participationRepository;
	
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
	
	@Test
	public void partSelectTest() {
		
	}
}
