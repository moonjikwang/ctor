package com.ctor.blindComments;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.entity.Blind;
import com.ctor.entity.BlindComments;
import com.ctor.repository.BlindCommentsRepository;

/**
 * @백승연
 * BlindCommentsRepository 연관관계 테스트
 * 
 * 1. 댓글등록
 * 2. 특정 번호의 익명댓글 & 익명글 내용 조회
 * 3. 메서드 바디를 이용한 테스트
 * 4. 특정번호가 가진 댓글 조회
 */

@SpringBootTest
public class BlindCommentsRepositoryTests {

	@Autowired
	private BlindCommentsRepository blindCommentsRepository;
	
	/* 1. addBComments()
	 * 더미데이터 40개 add -> rangeClosed(1, 40)
	 * 현재 블라인드 테이블 데이터 10개 -> (Math.random() * 10) + 1;
	 * 
	 * 블라인드테이블의 객체 & 블라인드코멘츠테이블 객체의 관계
	 * 블라인드테이블의 객체를 줘서 FK값을 기준으로 댓글 생성
	 * blindc_no값을 블라인드 객체의 blind_no값으로 설정
	 */
	@Test
	public void addBComments() {
		IntStream.rangeClosed(1, 40).forEach(i -> {
			
			long blindc_no = (long)(Math.random() * 10) + 1;
			
			Blind blind = Blind.builder().blind_no(blindc_no).build();
			
			//blindc_no값을 가진 블라인드객체를 블라인드코멘츠객체에 주입
			//공통키를 기준으로 블라인드에 존재하는 blind_no인 경우 데이터 Add
			BlindComments comments = BlindComments.builder()
					.blindc_text("익명댓글" + i)
					.blind(blind)
					.blindc_replyer("익명댓글작성자" + i)
					.build();
			blindCommentsRepository.save(comments);
		});
	}
	
	/* 2. BlindCommentRead1()
	 * DB에 있는 blindc_no 특정 번호 조회
	 * 특정 번호의 블라인드코멘츠 & 블라인드 내용 조회
	 */
	@Transactional
	@Test
	public void BlindCommentsReadTest() {
		
		//데이터베이스에 존재하는 번호
		Optional<BlindComments> result = blindCommentsRepository.findById(1L);
		
		BlindComments blindComments = result.get();
		
		System.out.println(blindComments);
		System.out.println(blindComments.getBlind());
	}
	
	//3. 메서드 바디를 이용한 테스트
	@Test
	public void CommentByBlindTest() {
		List<BlindComments> result = blindCommentsRepository.findByBlindBno(null);
		System.out.println(result.get(0));
	}
	
	//4. DB에 있는 blind_no의 특정 번호가 가진 댓글 조회
	@Test
	public void ListByBlindTest() {
		
		List<BlindComments> commentList = blindCommentsRepository.getCommentsByBlindOrderByCno(
											Blind.builder().blind_no(9L).build());
		commentList.forEach(blindComments -> System.out.println(blindComments));
	}
	
}
