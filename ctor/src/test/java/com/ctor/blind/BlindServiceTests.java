package com.ctor.blind;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.dto.BlindPageResultDTO;
import com.ctor.repository.BlindRepository;
import com.ctor.service.BlindService;
/**
 * 
 * @백승연
 * BlindService 테스트
 * 1. 글등록 : 특정회원이 하나의 게시물을 등록하는 테스트
 * 2. 목록처리 : 1페이지에 해당하는 10개의 게시글, 회원, 댓글의 수 처리
 * 3. 특정글조회(닉네임)
 * 4. 글삭제
 * 5. 글수정
 * 6. 로그인한 사용자가 작성한 게시글 조회
 */
@SpringBootTest
public class BlindServiceTests {

	@Autowired
	private BlindService blindService;
	
	@Autowired
	private BlindRepository blindRepository;
	//글등록
	@Test
	public void testRegister() {
		
		BlindDTO dto = BlindDTO.builder()
				.title("익명게시물제목12")
				.content("내용12")
				.writer("judy10@rabbit.com") //실제 DB에 있는 회원 email
				.build();
		
		Long blind_no = blindService.register(dto);
	}
	
	//글목록
	@Test
	public void testList() {
		
		BlindPageRequestDTO blindPageRequestDTO = new BlindPageRequestDTO();
		
		BlindPageResultDTO<BlindDTO, Object[]> result = blindService.getList(blindPageRequestDTO);
		
		for (BlindDTO blindDTO : result.getDtoList()) {
			System.out.println(blindDTO);
		}
	}
	
	//닉네임으로 조회
	@Test
	public void findByNickname() {
		
		String nickName = "닉네임2";
		
		BlindDTO blindDTO = blindService.findByNickname(nickName);
		
		System.out.println(blindDTO);
	}
	
	//글 삭제
	@Test
	public void deleteTest() {
		
		Long blind_no = 10L;
		blindService.removeWithComments(blind_no);
	}
	
	//글 수정
	@Test
	public void modifyTest() {
		
		BlindDTO blindDTO = BlindDTO.builder()
				.bno(11L)
				.title("제목 수정")
				.content("내용 수정")
				.build();
		
		blindService.modify(blindDTO);
	}
	
	@Test
	@DisplayName("로그인한 사용자가 작성한 익명게시글 조회하기")
	public void testGetMyBlinds() {
		String nickName = "친절한 동그라미";
		
		List<Object[]> myBlinds = blindRepository.findBlindByMemberNickname(nickName);
		
		assertThat(myBlinds).isNotEmpty();
	}
	
}
