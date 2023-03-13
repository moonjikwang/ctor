package com.ctor.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.entity.Member;
import com.ctor.repository.KakaoRepository;
import com.ctor.service.KakaoLoginService;


@SpringBootTest
public class MemberServiceTests {

	@Autowired
	private KakaoLoginService kakaoLoginService;
	@Autowired
	private KakaoRepository kakaoRepository;
	
	@Test
	public void MemberaddTests() {
		Member member = Member.builder().email("moonjikwang@naver.com").name("문지광").build();
		kakaoRepository.save(member);
	}
}
