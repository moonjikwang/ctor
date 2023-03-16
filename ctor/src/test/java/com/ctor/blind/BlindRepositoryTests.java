package com.ctor.blind;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ctor.entity.Blind;
import com.ctor.entity.Member;
import com.ctor.repository.BlindRepository;
import com.ctor.repository.KakaoRepository;
/**
 * 
 * @백승연
 * BlindRepository 연관관계 테스트
 * 1. 회원이 작성한 글 Insert
 * 2. DB에 있는 blind_no의 특정 번호 조회
 * 3. BlindRepository에 작성한 JPQL 테스트
 * 	3_1. 특정 게시물과 해당 게시물을 작성한 회원을 조회
 *  3_2. 특정 게시물과 해당 게시물에 속한 댓글들을 조회
 *  3_3. 목록화면에 필요한 블라인드&멤버&댓글수를 조회
 *  3_4. 조회화면에 필요한 블라인드&멤버&댓글수를 조회
 */
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
	
	/* testReadWithWriter()
	 * JPQL 1. 블라인드&멤버 조인한 결과를 조회하는 테스트 -> Blind의 writer변수를 이용해서 조인(연관관계O)
	 * 특정 게시물과 해당 게시물을 작성한 회원을 조회
	 */
	@Test
	public void testReadWithWriter() {
		
		Object result = blindRepository.getBlindWithWriter(1L);
		
		Object[] arr = (Object[])result;
		
		System.out.println("블라인드&멤버를 조인한 결과를 조회하는 테스트");
		System.out.println(Arrays.toString(arr));
	}
	
	/* testGetBlindWithComments()
	 * JPQL 2. 블라인드&코멘츠 조인한 결과를 조회하는 테스트 -> ON키워드를 이용한 직접조인(연관관계X)
	 * 특정 게시물과 해당 게시물에 속한 댓글들을 조회
	 */
	@Test
	public void testGetBlindWithComments() {
		
		List<Object[]> result = blindRepository.getBlindWithComments(1L);
		
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}
	
	/* testWithCommentsCount()
	 * JPQL 3. 목록화면에 필요한 블라인드&멤버&댓글수를 조회하는 테스트
	 * 한 페이지당 10개의 게시물, 내림차순 정렬
	 */
	@Test
	public void testWithCommentsCount() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("blind_no").descending());
		
		Page<Object[]> result = blindRepository.getBlindWithCommentsCount(pageable);
		
		result.get().forEach(row -> {
			Object[] arr = (Object[])row;
			System.out.println(Arrays.toString(arr));
		});
	}
	
	/* testRead()
	 * JPQL 4. 조회화면에 필요한 블라인드&멤버&댓글수를 조회하는 테스트
	 */
	@Test
	public void testRead() {
		
		Object result = blindRepository.getBlindByBlind_no(1L);
		
		Object[] arr = (Object[])result;
		
		System.out.println(Arrays.toString(arr));
	}
}
