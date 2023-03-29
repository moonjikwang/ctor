package com.ctor.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.repository.KakaoRepository;

@SpringBootTest
public class mtest {

	@Autowired
	KakaoRepository kakaoRepository;
	
	@Test
	public void remvoe() {
		kakaoRepository.delete(kakaoRepository.findById("aas@naver.com").get());
	}
}
