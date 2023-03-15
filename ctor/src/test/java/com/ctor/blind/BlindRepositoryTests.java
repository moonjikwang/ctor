package com.ctor.blind;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.entity.Blind;
import com.ctor.entity.Member;
import com.ctor.repository.BlindRepository;
import com.ctor.repository.KakaoRepository;

@SpringBootTest
public class BlindRepositoryTests {

	@Autowired
	private BlindRepository blindRepository;
	@Autowired
	private KakaoRepository kakaoRepository;

	/* BlindAdd()
	 * 더미데이터 회원이 작성한 글 1~10 add
	 * 1. 멤버테이블 안에 실제로 데이터가 있어야 한다 -> 멤버객체 생성
	 * 	  이메일이 있어야 데이터가 입력된다.(이메일 반드시 있어야함)
	 * 2. Blind 엔티티에서  @ManyToOne으로 Member를 변수 writer로 설정함
	 * 	  Member 테이블의 PK는 email -> Blind 테이블의 FK는 writer_email
	 */	
	@Test
	public void BlindAdd() {
		IntStream.rangeClosed(1, 10).forEach(i->{
			Member member = Member.builder()
					.email("judy" + i + "@rabbit.com")
					.name("이름" + i)
					.nickName("닉네임" + i)
					.grade("student")
					.subject("javaFullStack")
					.build();
			kakaoRepository.save(member);
			
			Blind blind = Blind.builder()
					.blind_title("제목" + i)
					.blind_content("내용"+ i)
					.writer(member)
					.build();
			blindRepository.save(blind);
		});
	}
	
	/* BlindRead1()
	 * DB에 있는 blind_no의 특정 번호 조회
	 */
	@Transactional
	@Test
	public void BlindRead1() {
		Optional<Blind> result = blindRepository.findById(1L);
		
		Blind blind = result.get();
		
		System.out.println(blind);
		System.out.println(blind.getWriter());
	}
	
	
	
}
